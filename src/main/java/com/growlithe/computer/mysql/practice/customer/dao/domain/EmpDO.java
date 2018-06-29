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
     * 上级empId
     */
    private Long parentEmpId;

    public EmpDO() {
    }

    public EmpDO(Long id, Long parentEmpId) {
        this.id = id;
        this.parentEmpId = parentEmpId;
    }

    public Long getParentEmpId() {
        return parentEmpId;
    }

    public void setParentEmpId(Long parentEmpId) {
        this.parentEmpId = parentEmpId;
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

    @Override
    public String toString() {
        return "EmpDO{" +
                "id=" + id +
                ", empId=" + empId +
                ", age=" + age +
                ", parentEmpId=" + parentEmpId +
                '}';
    }
}
