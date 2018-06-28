package com.growlithe.computer.mysql.enums;


/**
 * @Author : Growlithe
 * @Date : 2018/5/19 11:52
 * @Description
 */
public enum CapacityUnitEnum {
    /**
     * 容量单位
     */
    KB("K", "KB"),
    MB("M", "MB"),
    GB("G", "GB"),
    TB("T","TB");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDesc(String code) {
        if (code == null) {
            return null;
        }
        for (CapacityUnitEnum e : CapacityUnitEnum.values()) {
            if (e.getCode().equals(code)) {
                return e.getDesc();
            }
        }
        return null;
    }
    public static String getCode(String desc) {
        if (desc == null) {
            return null;
        }
        for (CapacityUnitEnum e : CapacityUnitEnum.values()) {
            if (e.getDesc().equals(desc)) {
                return e.getCode();
            }
        }
        return null;
    }

    CapacityUnitEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
