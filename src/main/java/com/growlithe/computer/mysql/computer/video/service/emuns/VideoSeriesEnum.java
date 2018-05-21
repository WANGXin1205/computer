package com.growlithe.computer.mysql.computer.video.service.emuns;

/**
 * @Author : Growlithe
 * @Date : 2018/5/19 11:36
 * @Description
 */
public enum VideoSeriesEnum {
    POKEMON("pokemon"),
    LEGAL_HIGH("legal_high"),
    SHERLOCK("sherlock"),
    LIE_TO_ME("lie_to_me");

    private String code;

    public String getCode() {
        return code;
    }

    VideoSeriesEnum(String code) {
        this.code = code;
    }
}
