package com.springboot.druid.util;

import org.apache.commons.lang.StringEscapeUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Dong.L
 * @Create: 2019-07-19 17:00
 * @Description: 字符串工具类，继承org.apache.commons.lang3.StringUtils类
 */
public class StringUtils {
    /**
     * 替换掉HTML标签方法
     * @param html
     * @return
     */
    public static String stripHtml(String html) {
        if (org.apache.commons.lang.StringUtils.isEmpty(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(html);
        String str = matcher.replaceAll("");
        return str;
    }

    /**
     * 缩略字符串（不区分中英文字符）
     * @param str-目标字符串
     * @param length-截取长度
     * @return
     */
    public static String abbr(String str, int length) {
        if (str == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : stripHtml(StringEscapeUtils.unescapeHtml(str)).toCharArray()) {
                currentLength += String.valueOf(c).getBytes("GBK").length;
                if (currentLength <= length -3) {
                    sb.append(c);
                } else {
                    sb.append("...");
                    break;
                }
            }
            return sb.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
