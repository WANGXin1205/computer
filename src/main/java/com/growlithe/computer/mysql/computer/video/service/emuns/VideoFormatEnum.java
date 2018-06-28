package com.growlithe.computer.mysql.computer.video.service.emuns;

/**
 * @Author : Growlithe
 * @Date : 2018/5/19 11:36
 * @Description
 */
public enum VideoFormatEnum {
    /**
     * 视频格式
     */
    SWF("swf"),
    MKV("mkv"),
    ISO("iso"),
    MP4("mp4"),
    RMVB("rmvb"),
    MOV("mov"),
    WMV("mwv"),
    AVI("avi"),
    M2ST("m2st");

    private String code;

    public String getCode() {
        return code;
    }

    VideoFormatEnum(String code) {
        this.code = code;
    }
}
