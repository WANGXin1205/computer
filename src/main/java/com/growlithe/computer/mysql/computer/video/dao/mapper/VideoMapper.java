package com.growlithe.computer.mysql.computer.video.dao.mapper;

import com.growlithe.computer.mysql.computer.video.dao.domain.VideoDO;

import java.util.List;

/**
 * @Author : Growlithe
 * @Date : 2018/5/19 18:06
 * @Description
 */
public interface VideoMapper {

    /**
     * 查询所有数据
     * @return
     */
    List<VideoDO> listAllVideoDO();

    /**
     * 批量保存
     * @param videoDOList
     * @return
     */
    Integer saveBatch(List<VideoDO> videoDOList);
}
