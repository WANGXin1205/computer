package com.growlithe.computer.mysql.practice.customer.dao.mapper;

import com.growlithe.computer.mysql.practice.customer.dao.domain.EmpDO;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

import java.util.List;

/**
 * @Author : Growlithe
 * @Date : 2018/6/3 20:30
 * @Description 用来搞spark
 */
public interface EmpMapper {

    /**
     * 查询所有雇员信息
     *
     * @return
     */
    List<EmpDO> listAllEmp();

    /**
     * 保存雇员信息
     *
     * @param empDOList
     * @return
     */
    Integer saveBatch(List<EmpDO> empDOList);

    /**
     * 更新雇员年龄信息
     *
     * @param empDOList
     * @return
     */
    Integer updateAgeByIdList(List<EmpDO> empDOList);

    /**
     * 直接删除所有数据，借口谨慎操作
     *
     * @return
     */
    Integer deleteAll();
}
