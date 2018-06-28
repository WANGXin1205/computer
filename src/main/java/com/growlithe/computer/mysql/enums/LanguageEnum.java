package com.growlithe.computer.mysql.enums;

/**
 * @Author : Growlithe
 * @Date : 2018/5/19 11:36
 * @Description
 */
public enum LanguageEnum {
    /**
     * 语言
     */
    CHINESE("chinese", "中文"),
    JAPANESE("japanese", "日语"),
    ENGLISH("english", "英语"),
    GERMAN("german","德语"),
    RUSSIAN("russian","俄语");

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
        for (LanguageEnum e : LanguageEnum.values()) {
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
        for (LanguageEnum e : LanguageEnum.values()) {
            if (e.getDesc().equals(desc)) {
                return e.getCode();
            }
        }
        return null;
    }

    LanguageEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
