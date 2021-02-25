package com.haso.common.system;


import com.haso.common.util.OConvertUtil;

/**
 * 查询链接规则
 *
 * @Author Sunjianlei
 */
public enum MatchTypeEnum {

    AND("AND"),
    OR("OR");

    private String value;

    MatchTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MatchTypeEnum getByValue(Object value) {
        if (OConvertUtil.isEmpty(value)) {
            return null;
        }
        return getByValue(value.toString());
    }

    public static MatchTypeEnum getByValue(String value) {
        if (OConvertUtil.isEmpty(value)) {
            return null;
        }
        for (MatchTypeEnum val : values()) {
            if (val.getValue().toLowerCase().equals(value.toLowerCase())) {
                return val;
            }
        }
        return null;
    }
}
