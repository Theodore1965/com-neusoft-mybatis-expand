package com.neusoft.mybatis.expand.utils.excel;

import com.neusoft.mybatis.expand.global.BusinessException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleXlsxWriter
{
    private Workbook workbook;
    private Sheet sheet;
    private int rowCount;
    private CellStyle style;

    public SimpleXlsxWriter() {
        this(ExcelType.XLSX);
    }

    public SimpleXlsxWriter( ExcelType excelType) {
        this.workbook = null;
        this.sheet = null;
        this.rowCount = 0;
        this.style = null;
        Assert.notNull(excelType, "excelType不能为空。");
        if (ExcelType.XLSX.equals(excelType)) {
            this.workbook = new XSSFWorkbook();
        }
        else if (ExcelType.XLS.equals(excelType)) {
            this.workbook = new HSSFWorkbook();
        }
        this.sheet = this.workbook.createSheet();
    }

    public Workbook getWorkbook() {
        return this.workbook;
    }

    public void addRecord(String[] values) {
        Assert.notNull(values, "values不能为空。");
         Row row = this.sheet.createRow(this.rowCount);
        for (int i = 0; i < values.length; ++i) {
             Cell cell = row.createCell(i);
            cell.setCellStyle(this.getTextCellStyle());
            if (values[i] != null) {
                cell.setCellValue(values[i]);
            }
        }
        ++this.rowCount;
    }

    public void write( OutputStream stream) {
        try {
            this.workbook.write(stream);
        }
        catch (IOException ex) {
            throw new BusinessException(ex);
        }
    }

    public void close() {
        try {
            this.workbook = null;
            this.sheet = null;
        }
        catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    private CellStyle getTextCellStyle() {
        if (this.style != null) {
            return this.style;
        }
         CellStyle style = this.workbook.createCellStyle();
         DataFormat format = this.workbook.createDataFormat();
        style.setDataFormat(format.getFormat("@"));
        style.setWrapText(true);
        return style;
    }
}
