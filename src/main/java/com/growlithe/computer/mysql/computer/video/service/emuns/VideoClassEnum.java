package com.growlithe.computer.mysql.computer.video.service.emuns;

/**
 * @Author : Growlithe
 * @Date : 2018/5/19 11:36
 * @Description
 */
public enum VideoClassEnum {

    CARTOON("cartoon", "卡通"),
    DOC("documentary", "纪录片"),
    TV("tv", "电视剧"),
    MOVIE("movie", "电影"),
    OPEN_CLASS("open_class", "公开课"),
    MV("mv","mv"),
    CELEBRATION("celebration","庆典"),
    CONCERT("concert","音乐会"),
    OTHER("other","其他");


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
        for (VideoClassEnum e : VideoClassEnum.values()) {
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
        for (VideoClassEnum e : VideoClassEnum.values()) {
            if (e.getDesc().equals(desc)) {
                return e.getCode();
            }
        }
        return null;
    }

    VideoClassEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
