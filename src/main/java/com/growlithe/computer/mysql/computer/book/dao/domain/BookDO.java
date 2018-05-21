package com.growlithe.computer.mysql.computer.book.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @Author : Growlithe
 * @Date : 2018/5/19 11:22
 * @Description
 */
public class BookDO implements Serializable {

    /**
     * 主键
     */
    private Long id;
    /**
     * 书名
     */
    private String bookName;
    /**
     * 书英文名
     */
    private String bookEnglishName;
    /**
     * 书籍分类
     */
    private String bookClass;
    /**
     * 书籍二级分类
     */
    private String bookSubordinateClass;
    /**
     * 书籍作者
     */
    private String bookAuthor;
    /**
     * 书籍格式
     */
    private String bookFormat;
    /**
     * 书籍容量大小
     */
    private BigDecimal bookCapacity;
    /**
     * 容量单位
     */
    private String capacityUnit;
    /**
     * 状态
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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookEnglishName() {
        return bookEnglishName;
    }

    public void setBookEnglishName(String bookEnglishName) {
        this.bookEnglishName = bookEnglishName;
    }

    public String getBookClass() {
        return bookClass;
    }

    public void setBookClass(String bookClass) {
        this.bookClass = bookClass;
    }

    public String getBookSubordinateClass() {
        return bookSubordinateClass;
    }

    public void setBookSubordinateClass(String bookSubordinateClass) {
        this.bookSubordinateClass = bookSubordinateClass;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookFormat() {
        return bookFormat;
    }

    public void setBookFormat(String bookFormat) {
        this.bookFormat = bookFormat;
    }

    public BigDecimal getBookCapacity() {
        return bookCapacity;
    }

    public void setBookCapacity(BigDecimal bookCapacity) {
        this.bookCapacity = bookCapacity;
    }

    public String getCapacityUnit() {
        return capacityUnit;
    }

    public void setCapacityUnit(String capacityUnit) {
        this.capacityUnit = capacityUnit;
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
        return "BookDO{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookEnglishName='" + bookEnglishName + '\'' +
                ", bookClass='" + bookClass + '\'' +
                ", bookSubordinateClass='" + bookSubordinateClass + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookFormat='" + bookFormat + '\'' +
                ", bookCapacity=" + bookCapacity +
                ", capacityUnit='" + capacityUnit + '\'' +
                ", status=" + status +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
