package com.growlithe.computer.mysql.computer.video.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author : Growlithe
 * @Date : 2018/6/22 23:04
 * @Description
 */
public class MusicDO implements Serializable {

    /**
     * 主键
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 语言 Chinese-中文 Japanese-日文 English-英文 null-纯音乐
     */
    private String language;
    /**
     * 作者 歌手
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
     * 是否音乐还是mv 1 音乐 2 mv
     */
    private Integer musicOrMv;
    /**
     * 格式 iso mp4 avi wmv rmvb flac mp3 ape
     */
    private String format;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public Integer getMusicOrMv() {
        return musicOrMv;
    }

    public void setMusicOrMv(Integer musicOrMv) {
        this.musicOrMv = musicOrMv;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
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
        return "MusicDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", author='" + author + '\'' +
                ", capacity=" + capacity +
                ", capacityUnit='" + capacityUnit + '\'' +
                ", musicOrMv=" + musicOrMv +
                ", format='" + format + '\'' +
                ", status=" + status +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
