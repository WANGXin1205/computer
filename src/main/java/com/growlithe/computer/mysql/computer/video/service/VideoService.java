package com.growlithe.computer.mysql.computer.video.service;

import com.growlithe.computer.common.CandyResult;
import com.growlithe.computer.mysql.computer.video.dao.domain.VideoDO;
import com.growlithe.computer.mysql.computer.video.dao.mapper.VideoMapper;
import com.growlithe.computer.mysql.enums.CapacityUnitEnum;
import com.growlithe.computer.utils.StorageUnitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Author : Growlithe
 * @Date : 2018/5/21 22:36
 * @Description
 */
@Service
public class VideoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoService.class);

    @Resource
    private VideoMapper videoMapper;

    /**
     * 计算目前统计的视频占用磁盘空间大小
     * @return
     */
    public CandyResult<BigDecimal> getAllCapacity(){
        CandyResult<BigDecimal> candyResult = new CandyResult<>();

        var allVideoList = videoMapper.listAllVideoDO();

        if (CollectionUtils.isEmpty(allVideoList)){
            candyResult.setData(BigDecimal.ZERO);
            candyResult.setSuccess(true);
            return candyResult;
        }

        BigDecimal kbCapacity = allVideoList.stream().filter(x-> x.getCapacityUnit().equals(CapacityUnitEnum.KB.getCode()))
                .map(VideoDO::getCapacity).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        BigDecimal mbCapacity = allVideoList.stream().filter(x-> x.getCapacityUnit().equals(CapacityUnitEnum.MB.getCode()))
                .map(VideoDO::getCapacity).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        BigDecimal gbCapacity = allVideoList.stream().filter(x-> x.getCapacityUnit().equals(CapacityUnitEnum.GB.getCode()))
                .map(VideoDO::getCapacity).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        BigDecimal tbCapacity = allVideoList.stream().filter(x-> x.getCapacityUnit().equals(CapacityUnitEnum.TB.getCode()))
                .map(VideoDO::getCapacity).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        BigDecimal gbFromKB = StorageUnitUtils.convertKBToGB(kbCapacity);
        BigDecimal gbFromMB = StorageUnitUtils.convertMBToGB(mbCapacity);
        BigDecimal gbFromTB = StorageUnitUtils.convertTBToGB(tbCapacity);

        BigDecimal sum = gbCapacity.add(gbFromKB).add(gbFromMB).add(gbFromTB);

        candyResult.setData(sum);
        candyResult.setSuccess(true);
        return candyResult;
    }

}
