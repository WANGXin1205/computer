package com.growlithe.computer.mysql.computer.dictionary.service;

import com.growlithe.computer.common.CandyResult;
import com.growlithe.computer.mysql.computer.dictionary.dao.domain.DictionaryDO;
import com.growlithe.computer.mysql.computer.dictionary.dao.domain.DictionaryItemDO;
import com.growlithe.computer.mysql.computer.dictionary.dao.mapper.DictionaryItemMapper;
import com.growlithe.computer.mysql.computer.dictionary.dao.mapper.DictionaryMapper;
import com.growlithe.computer.mysql.computer.dictionary.service.dto.DictionaryDTO;
import com.growlithe.computer.mysql.computer.video.dao.domain.VideoDO;
import com.growlithe.computer.mysql.computer.video.dao.mapper.VideoMapper;
import com.growlithe.computer.mysql.computer.video.service.emuns.VideoClassEnum;
import com.growlithe.computer.mysql.enums.CapacityUnitEnum;
import com.growlithe.computer.utils.StorageUnitUtils;
import org.bouncycastle.asn1.cmp.CAKeyUpdAnnContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author : Growlithe
 * @Date : 2018/5/21 22:36
 * @Description
 */
@Service
public class DictionaryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryService.class);

    @Resource
    private DictionaryMapper dictionaryMapper;
    @Resource
    private DictionaryItemMapper dictionaryItemMapper;

    /**
     * 字典表的保存
     * @param createBy
     * @param dictionaryName
     * @param dictionaryItemDOList
     * @return
     */
    @Transactional(value = "mysqlTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public CandyResult<Void> saveBatch(String createBy, String dictionaryName, List<DictionaryItemDO> dictionaryItemDOList){
        CandyResult<Void> candyResult = new CandyResult<>();

        CandyResult<DictionaryDTO> checkAndProcessDataInSaveBatchResult = this.checkAndProcessDataInSaveBatch(createBy,dictionaryName,dictionaryItemDOList);
        if (!checkAndProcessDataInSaveBatchResult.isSuccess()){
            candyResult.setMessage(checkAndProcessDataInSaveBatchResult.getMessage());
            return candyResult;
        }

        DictionaryDTO dictionaryDTO = checkAndProcessDataInSaveBatchResult.getData();

        CandyResult<Void> algorithmInSaveBatchResult = this.algorithmInSaveBatch(dictionaryDTO);
        if (!algorithmInSaveBatchResult.isSuccess()){
            LOGGER.warn(algorithmInSaveBatchResult.getMessage());
            candyResult.setMessage(algorithmInSaveBatchResult.getMessage());
            return candyResult;
        }

        candyResult.setSuccess(true);
        return candyResult;
    }

    /**
     * 字典表的保存 校验数据
     * @param createBy
     * @param dictionaryName
     * @param dictionaryItemDOList
     * @return
     */
    private CandyResult<DictionaryDTO> checkAndProcessDataInSaveBatch(String createBy, String dictionaryName, List<DictionaryItemDO> dictionaryItemDOList){
        CandyResult<DictionaryDTO> candyResult = new CandyResult<>();

        if (StringUtils.isEmpty(createBy)){
            candyResult.setMessage("创建人为空");
            return candyResult;
        }
        if (StringUtils.isEmpty(dictionaryName)){
            candyResult.setMessage("要保存的字典表名称为空");
            return candyResult;
        }


        if (CollectionUtils.isEmpty(dictionaryItemDOList)){
            candyResult.setMessage("要保存的字典项目表为空");
            return candyResult;
        }

        int row = 1;
        for (DictionaryItemDO x:dictionaryItemDOList){
            if (x.getItemName() == null){
                candyResult.setMessage("字典项目表，第" + row +"行，名称为空");
                return candyResult;
            }

            x.setCreateBy(createBy);

            row++;
        }

        DictionaryDO dictionaryDO = new DictionaryDO();
        dictionaryDO.setName(dictionaryName);
        dictionaryDO.setUpdateBy(createBy);

        DictionaryDTO dictionaryDTO = new DictionaryDTO();
        dictionaryDTO.setDictionaryDO(dictionaryDO);
        dictionaryDTO.setDictionaryItemDOList(dictionaryItemDOList);

        candyResult.setSuccess(true);
        candyResult.setData(dictionaryDTO);
        return candyResult;
    }

    /**
     * 保存字典和字典项目表
     * @param dictionaryDTO
     * @return
     */
    private CandyResult<Void> algorithmInSaveBatch(DictionaryDTO dictionaryDTO){
        CandyResult<Void> candyResult  = new CandyResult<>();

        var dictionaryDO = dictionaryDTO.getDictionaryDO();
        var list = dictionaryDTO.getDictionaryItemDOList();

        // 保存字典表主表
        dictionaryMapper.save(dictionaryDO);

        // 获取字典表主表 id
        Integer dictionaryId = dictionaryDO.getId();

        // 字典项目表赋予 主表id
        list.forEach(x->x.setDictionaryId(dictionaryId));

        // 保存字典项目表
        dictionaryItemMapper.saveBatch(list);

        candyResult.setSuccess(true);
        return candyResult;
    }

}
