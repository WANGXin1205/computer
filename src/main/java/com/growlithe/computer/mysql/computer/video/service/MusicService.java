package com.growlithe.computer.mysql.computer.video.service;

import com.growlithe.computer.common.CandyResult;
import com.growlithe.computer.mysql.computer.video.dao.domain.MusicDO;
import com.growlithe.computer.mysql.computer.video.dao.domain.VideoDO;
import com.growlithe.computer.mysql.computer.video.dao.mapper.MusicMapper;
import com.growlithe.computer.mysql.computer.video.dao.mapper.VideoMapper;
import com.growlithe.computer.mysql.enums.CapacityUnitEnum;
import com.growlithe.computer.utils.StorageUnitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author : Growlithe
 * @Date : 2018/5/21 22:36
 * @Description
 */
@Service
public class MusicService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MusicService.class);

    @Resource
    private MusicMapper musicMapper;

    @Transactional(value = "mysqlTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public CandyResult<Void> saveBatch(List<MusicDO> musicDOList){
        CandyResult<Void> candyResult = new CandyResult<>();

        // data preProcess
        // 检查重复的歌曲
        // 筛选出数据中未保存的歌曲

        // algorithm
        // 保存数据

        candyResult.setSuccess(true);
        return candyResult;
    }

}
