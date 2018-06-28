package com.growlithe.computer.mysql.computer.book.service.enums;

/**
 * @Author : Growlithe
 * @Date : 2018/5/19 11:36
 * @Description
 */
public enum BookClassEnum {
    /**
     * 图书分类
     */
    ACADEMIC("academic", "学术"),
    CARTOON("cartoon", "卡通"),
    LITERATURE("literature", "文学"),
    OTHER("other", "其他（杂书）"),
    TECHNOLOGY("technology", "技术");

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
        for (BookClassEnum e : BookClassEnum.values()) {
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
        for (BookClassEnum e : BookClassEnum.values()) {
            if (e.getDesc().equals(desc)) {
                return e.getCode();
            }
        }
        return null;
    }

    BookClassEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
