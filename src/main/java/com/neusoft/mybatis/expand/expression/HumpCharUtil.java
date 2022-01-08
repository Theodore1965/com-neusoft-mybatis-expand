package com.neusoft.mybatis.expand.expression;

public class HumpCharUtil {

    public static final char UNDERLINE = '_';

    /**
     * 大写
     */
    public static Integer upperValue = 2;

    /**
     * 小写
     */
    public static Integer lowerValue = 1;

    /**
     * 驼峰转大写下划线
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        return camelToUnderline(param, upperValue);
    }

    /**
     * 驼峰转下划线
     * @param param
     * @param charType
     * @return
     */
    public static String camelToUnderline(String param, Integer charType) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
            }
            if (charType == upperValue) {
                sb.append(Character.toUpperCase(c));  //统一都转大写
            } else if (charType == lowerValue) {
                sb.append(Character.toLowerCase(c));  //统一都转小写
            }
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        Boolean flag = false; // "_" 后转大写标志,默认字符前面没有"_"
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                flag = true;
                continue;   //标志设置为true,跳过
            } else {
                if (flag == true) {
                    //表示当前字符前面是"_" ,当前字符转大写
                    sb.append(Character.toUpperCase(param.charAt(i)));
                    flag = false;  //重置标识
                } else {
                    sb.append(Character.toLowerCase(param.charAt(i)));
                }
            }
        }
        return sb.toString();
    }
}
