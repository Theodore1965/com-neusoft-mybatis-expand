package com.neusoft.mybatis.expand.utils.excel;

import com.neusoft.mybatis.expand.global.BusinessException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Assert;

import java.io.InputStream;
import java.util.*;

public class SimpleXlsxReader {
    private Workbook workbook;
    private String[] headers;
    private Iterator<String[]> recordIterator;
    private String[] currentRecord;
    private Map<String, Integer> headerIndexs;

    public SimpleXlsxReader(InputStream inputStream) {
        this(inputStream, ExcelType.XLSX);
    }

    public SimpleXlsxReader(InputStream inputStream, ExcelType excelType) {
        this.workbook = null;
        this.headers = null;
        this.recordIterator = null;
        this.currentRecord = null;
        this.headerIndexs = new HashMap<String, Integer>();
        Assert.notNull(inputStream, "参数inputStream不能为空");
        Assert.notNull(excelType, "参数excelType不能为空");
        try {
            if (ExcelType.XLSX.equals(excelType)) {
                this.workbook = new XSSFWorkbook(inputStream);
            } else if (ExcelType.XLS.equals(excelType)) {
                this.workbook = new HSSFWorkbook(inputStream);
            }
            Sheet sheet = this.workbook.getSheetAt(0);
            List<String[]> records = new ArrayList<String[]>();
            Map<Integer, String> cellHeaderColumnIndexs = new HashMap<Integer, String>();
            int columnCount = 0;
            int i = 0;
            for (Row row : sheet) {
                if (i++ == 0) {
                    for (Cell cell : row) {
                        String header = this.getCellValue(cell);
                        this.headers = ArrayUtils.add(this.headers, header);
                        cellHeaderColumnIndexs.put(cell.getColumnIndex(), header);
                        columnCount = ((cell.getColumnIndex() + 1 > columnCount) ? (cell.getColumnIndex() + 1) : columnCount);
                    }
                    Set<String> set = new HashSet<String>();
                    String[] headers;
                    for (int length = (headers = this.headers).length, j = 0; j < length; ++j) {
                        String h = headers[j];
                        set.add(h);
                    }
                    if (set.size() < this.headers.length) {
                        throw new BusinessException("表头出现相同列名");
                    }
                    continue;
                } else {
                    String[] values = new String[this.headers.length];
                    for (int k = 0; k < columnCount; ++k) {
                        String header = cellHeaderColumnIndexs.get(k);
                        if (header != null) {
                            Cell cell2 = row.getCell(k);
                            values[this.getIndex(header)] = this.getCellValue(cell2);
                        }
                    }
                    records.add(values);
                }
            }
            this.recordIterator = records.iterator();
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    public String[] getHeaders() {
        return this.headers;
    }

    public String getHeader(int index) {
        return this.headers[index];
    }

    public int getIndex(String headerName) {
        Integer index = this.headerIndexs.get(headerName);
        if (index != null) {
            return index;
        }
        int idx = -1;
        for (int i = 0; i < this.headers.length; ++i) {
            if (StringUtils.equals(this.headers[i], headerName)) {
                idx = i;
                break;
            }
        }
        this.headerIndexs.put(headerName, idx);
        return idx;
    }

    public boolean readRecord() {
        boolean hasNext = this.recordIterator.hasNext();
        if (!hasNext) {
            return false;
        }
        this.currentRecord = this.recordIterator.next();
        return true;
    }

    public String[] getCurrentRecord() {
        return this.currentRecord;
    }

    public String get(int index) {
        return this.currentRecord[index];
    }

    public String get(String headerName) {
        int index = this.getIndex(headerName);
        if (index == -1) {
            return null;
        }
        return this.currentRecord[index];
    }

    public void close() {
        try {
            this.workbook = null;
            this.headers = null;
            this.recordIterator = null;
            this.currentRecord = null;
            if (this.headerIndexs == null) {
                this.headerIndexs.clear();
            }
            this.headerIndexs = null;
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    private String getCellValue(Cell cell) {
        if (cell != null) {
            Object result = "";
            switch (cell.getCellType().getCode()) {
                case 1: {
                    result = cell.getStringCellValue();
                    break;
                }
                case 0: {
                    HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
                    result = dataFormatter.formatCellValue(cell);
                    break;
                }
                case 4: {
                    result = cell.getBooleanCellValue();
                    break;
                }
                case 2: {
                    result = cell.getCellFormula();
                    break;
                }
                case 5: {
                    result = cell.getErrorCellValue();
                }
            }
            return StringUtils.trimToNull(result.toString());
        }
        return null;
    }
}
