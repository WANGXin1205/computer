package com.growlithe.computer.mysql.computer.book.service.enums;

/**
 * @Author : Growlithe
 * @Date : 2018/5/19 11:36
 * @Description
 */
public enum BookSubordinateClassEnum {
    ACADEMIC_MATHS("a_m", "学术-数学"),
    LITERATURE_CHINESE_ANCIENT("l_c_a", "中国古代文学"),
    LITERATURE_CHINESE_MODERN("l_c_m", "中国现代文学"),
    LITERATURE_CHINESE_NOW("l_c_n", "中国当代文学");

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
        for (BookSubordinateClassEnum e : BookSubordinateClassEnum.values()) {
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
        for (BookSubordinateClassEnum e : BookSubordinateClassEnum.values()) {
            if (e.getDesc().equals(desc)) {
                return e.getCode();
            }
        }
        return null;
    }

    BookSubordinateClassEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
