package com.growlithe.computer.mysql.computer.book.service.enums;

/**
 * @Author : Growlithe
 * @Date : 2018/5/19 11:36
 * @Description
 */
public enum BookFormatEnum {
    TXT("txt"),
    PDF("pdf"),
    CAJ("caj"),
    EXE("exe");

    private String code;

    public String getCode() {
        return code;
    }

    BookFormatEnum(String code) {
        this.code = code;
    }
}
