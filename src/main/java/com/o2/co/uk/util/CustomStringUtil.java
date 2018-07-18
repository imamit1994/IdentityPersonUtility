package com.o2.co.uk.util;

import org.springframework.util.StringUtils;

import java.util.List;
import java.util.StringJoiner;

public class CustomStringUtil {

   static public String getStringIfPresentElseEmpty(String value) {
        return StringUtils.isEmpty(value) ? "" : value;
    }

    static public String getStringFormatFromList(List<? extends StringFormatterInterface> list) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        list.forEach(customerAudit -> { stringJoiner.add(customerAudit.getFieldsInStringFormat()); });
       return  stringJoiner.toString();
    }
}
