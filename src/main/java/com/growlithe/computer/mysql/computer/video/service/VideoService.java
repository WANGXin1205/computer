package com.growlithe.computer.mysql.computer.video.service;

import com.growlithe.computer.common.CandyResult;
import com.growlithe.computer.mysql.computer.video.dao.domain.VideoDO;
import com.growlithe.computer.mysql.computer.video.dao.mapper.VideoMapper;
import com.growlithe.computer.mysql.computer.video.service.emuns.VideoClassEnum;
import com.growlithe.computer.mysql.enums.CapacityUnitEnum;
import com.growlithe.computer.utils.StorageUnitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class VideoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoService.class);

    @Resource
    private VideoMapper videoMapper;

    /**
     * 查询所有的视频
     *
     * @return
     */
    public CandyResult<List<VideoDO>> listAllVideo() {
        CandyResult<List<VideoDO>> candyResult = new CandyResult<>();

        var allVideoList = videoMapper.listAllVideoDO();

        candyResult.setData(allVideoList);
        candyResult.setSuccess(true);
        return candyResult;
    }


    /**
     * 计算目前统计的视频占用磁盘空间大小
     *
     * @return
     */
    public CandyResult<BigDecimal> getAllCapacity() {
        CandyResult<BigDecimal> candyResult = new CandyResult<>();

        var allVideoList = videoMapper.listAllVideoDO();

        BigDecimal sum = this.computerVideoCapacity(allVideoList);

        candyResult.setData(sum);
        candyResult.setSuccess(true);
        return candyResult;
    }

    /**
     * 计算目前统计的视频占用磁盘空间大小（通过videoClassEnum）
     *
     * @param videoClassEnum
     * @return
     */
    public CandyResult<BigDecimal> getCapacity(VideoClassEnum videoClassEnum) {
        CandyResult<BigDecimal> candyResult = new CandyResult<>();

        var allVideoList = videoMapper.listVideoDO(videoClassEnum.getCode());

        BigDecimal sum = this.computerVideoCapacity(allVideoList);

        candyResult.setData(sum);
        candyResult.setSuccess(true);
        return candyResult;
    }


    /**
     * 计算视频容量
     *
     * @param videoDOList
     * @return
     */
    private BigDecimal computerVideoCapacity(List<VideoDO> videoDOList){

        if (CollectionUtils.isEmpty(videoDOList)){
            return BigDecimal.ZERO;
        }

        BigDecimal kbCapacity = BigDecimal.ZERO;
        BigDecimal mbCapacity = BigDecimal.ZERO;
        BigDecimal gbCapacity = BigDecimal.ZERO;
        BigDecimal tbCapacity = BigDecimal.ZERO;

        for (VideoDO x : videoDOList) {
            BigDecimal tempCapacity = x.getCapacity() != null ? x.getCapacity() : BigDecimal.ZERO;
            if (x.getCapacityUnit().equals(CapacityUnitEnum.KB.getCode())) {
                kbCapacity = kbCapacity.add(tempCapacity);
            }
            if (x.getCapacityUnit().equals(CapacityUnitEnum.MB.getCode())) {
                mbCapacity = mbCapacity.add(tempCapacity);
            }
            if (x.getCapacityUnit().equals(CapacityUnitEnum.GB.getCode())) {
                gbCapacity = mbCapacity.add(tempCapacity);
            }
            if (x.getCapacityUnit().equals(CapacityUnitEnum.TB.getCode())) {
                tbCapacity = mbCapacity.add(tempCapacity);
            }
        }

        BigDecimal gbFromKB = StorageUnitUtils.convertKBToGB(kbCapacity);
        BigDecimal gbFromMB = StorageUnitUtils.convertMBToGB(mbCapacity);
        BigDecimal gbFromTB = StorageUnitUtils.convertTBToGB(tbCapacity);

        BigDecimal sum = gbCapacity.add(gbFromKB).add(gbFromMB).add(gbFromTB);

        return sum;
    }

}
