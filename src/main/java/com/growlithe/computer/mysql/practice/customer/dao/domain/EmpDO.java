package com.growlithe.computer.mysql.practice.customer.dao.domain;

/**
 * @Author : Growlithe
 * @Date : 2018/6/3 20:28
 * @Description
 */
public class EmpDO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 雇员id
     */
    private Long empId;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * todo 上级id，这个字段临时凑数
     */
    private Long parentId;

    public EmpDO() {
    }

    public EmpDO(Long id, Long parentId) {
        this.id = id;
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "EmpDO{" +
                "id=" + id +
                ", empId=" + empId +
                ", age=" + age +
                ", parentId=" + parentId +
                '}';
    }
}
