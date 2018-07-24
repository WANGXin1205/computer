package com.growlithe.computer.mysql.practice.customer.service;

import com.growlithe.computer.common.CandyResult;
import com.growlithe.computer.mysql.practice.customer.dao.domain.EmpDO;
import com.growlithe.computer.mysql.practice.customer.dao.mapper.CustomerMapper;
import com.growlithe.computer.mysql.practice.customer.dao.mapper.EmpMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : Growlithe
 * @Date : 2018/7/24 14:24
 * @Description
 */
@Service("empService")
public class EmpService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmpService.class);

    @Autowired
    private EmpMapper empMapper;

    /**
     * 简单的插入
     *
     * @param empDOList
     * @return
     */
    @Transactional(value = "mysqlTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public CandyResult saveBatch(List<EmpDO> empDOList) {
        CandyResult candyResult = new CandyResult();

        empMapper.saveBatch(empDOList);

        candyResult.setSuccess(true);
        return candyResult;
    }

}
