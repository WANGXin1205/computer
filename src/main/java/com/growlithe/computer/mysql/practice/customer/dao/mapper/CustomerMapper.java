package com.growlithe.computer.mysql.practice.customer.dao.mapper;


import com.growlithe.computer.mysql.practice.customer.dao.domain.CustomerDO;
import com.growlithe.computer.mysql.practice.customer.dao.domain.EmpDO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author : Growlithe
 * @Date : 2018/5/18 21:30
 * @Description CustomerMapper.xml 为什么是文件夹，我一直很懵逼
 */
public interface CustomerMapper {

    /**
     * 查询所有用户
     * @return
     */
    List<CustomerDO> listAllCustomerDO();

    /**
     * 保存用户信息
     * @param customerDOList
     * @return
     */
    Integer saveBatch(List<CustomerDO> customerDOList);
}
