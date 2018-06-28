package com.growlithe.computer.mysql.computer.video.dao.mapper;

import com.growlithe.computer.mysql.computer.video.dao.domain.MusicDO;
import com.growlithe.computer.mysql.computer.video.dao.domain.VideoDO;

import java.util.List;

/**
 * @Author : Growlithe
 * @Date : 2018/5/19 18:06
 * @Description
 */
public interface MusicMapper {

    /**
     * 查询所有数据
     * @return
     */
    List<MusicDO> listAllMusicDO();

    /**
     * 修正数据用，提取Capacity字段，更新为Capacity和Capacity_unit两个字段
     * @param musicDOList
     * @return
     */
    Integer updateCapacity(List<MusicDO> musicDOList);

    /**
     * 批量保存
     * @param musicDOList
     * @return
     */
    Integer saveBatch(List<MusicDO> musicDOList);
}
