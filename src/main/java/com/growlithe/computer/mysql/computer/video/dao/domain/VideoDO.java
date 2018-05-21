package com.growlithe.computer.mysql.computer.video.dao.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author : Growlithe
 * @Date : 2018/5/19 17:48
 * @Description
 */
public class VideoDO {

    /**
     * 主键
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 英文名称
     */
    private String englishName;
    /**
     * 作者，主讲人
     */
    private String author;
    /**
     * 容量大小
     */
    private BigDecimal capacity;
    /**
     * 容量单位
     */
    private String capacityUnit;
    /**
     * 格式 iso mp4 avi wmv rmvb
     */
    private String format;
    /**
     * 语言 Chinese-中文 Japanese-日文 English-英文
     */
    private String language;
    /**
     * 品质 1080P 720P 480P
     */
    private String quality;
    /**
     * 类别 cartoon-卡通 tv-电视剧 movie-电影 open_class-公开课 documentary-纪录片 mv-mv
     */
    private String videoClass;
    /**
     * 系列
     */
    private String series;
    /**
     * 状态 1-有效 0-失效
     */
    private Integer status;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     *  更新时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getCapacity() {
        return capacity;
    }

    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }

    public String getCapacityUnit() {
        return capacityUnit;
    }

    public void setCapacityUnit(String capacityUnit) {
        this.capacityUnit = capacityUnit;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getQuanlity() {
        return quality;
    }

    public void setQuanlity(String quanlity) {
        this.quality = quanlity;
    }

    public String getVideoClass() {
        return videoClass;
    }

    public void setVideoClass(String videoClass) {
        this.videoClass = videoClass;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "VideoDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", author='" + author + '\'' +
                ", capacity=" + capacity +
                ", capacityUnit='" + capacityUnit + '\'' +
                ", format='" + format + '\'' +
                ", language='" + language + '\'' +
                ", quality='" + quality + '\'' +
                ", videoClass='" + videoClass + '\'' +
                ", series='" + series + '\'' +
                ", status=" + status +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
