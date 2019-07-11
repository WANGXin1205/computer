package com.growlithe.computer.mysql.computer.dictionary.dao.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @Author : Growlithe
 * @Date : 2019/1/11 18:06
 * @Description 字典项目表
 */
public class DictionaryItemDO implements Serializable {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 项目名称
     */
    private String itemName;
    /**
     * 字典表id
     */
    private Integer dictionaryId;
    /**
     * 数据状态
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
     * 更新时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Integer dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
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
        this.createBy = createBy == null ? null : createBy.trim();
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
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DictionaryItemDO that = (DictionaryItemDO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dictionaryId, that.dictionaryId) &&
                Objects.equals(itemName, that.itemName) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dictionaryId, itemName, status, createBy, createTime, updateBy, updateTime);
    }

    @Override
    public String toString() {
        return "DictionaryItemDO{" +
                "id=" + id +
                ", dictionaryId=" + dictionaryId +
                ", itemName='" + itemName + '\'' +
                ", status=" + status +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}