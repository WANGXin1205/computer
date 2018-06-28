package com.growlithe.computer.mysql.computer.video.service.emuns;

/**
 * @Author : Growlithe
 * @Date : 2018/5/19 11:36
 * @Description
 */
public enum VideoQualityEnum {
    /**
     * 视频质量
     */
    LOW("480P"),
    MIDDLE("720P"),
    HIGH("1080P");

    private String code;

    public String getCode() {
        return code;
    }

    VideoQualityEnum(String code) {
        this.code = code;
    }
}
