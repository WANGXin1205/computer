package com.growlithe.computer.config.utils;

import com.growlithe.computer.mysql.practice.customer.dao.mapper.CustomerMapper;

/**
 * @Author : Growlithe
 * @Date : 2018/7/5 0:20
 * @Description
 */
public class AutowiredUtils {

    public static CustomerMapper customerMapper = (CustomerMapper) MySqlDataSourceConfigUtils.applicationContext.getBean("customerMapper");
}
