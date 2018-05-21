package com.growlithe.computer.mysql.practice.customer.dao.mapper;


import com.growlithe.computer.mysql.practice.customer.dao.domain.CustomerDO;

import java.util.List;

/**
 * @Author : Growlithe
 * @Date : 2018/5/18 21:30
 * @Description
 */
public interface CustomerMapper {

    List<CustomerDO> listAllCustomerDO();
}
