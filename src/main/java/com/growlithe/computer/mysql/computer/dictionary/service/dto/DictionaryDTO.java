package com.growlithe.computer.mysql.computer.dictionary.service.dto;

import com.growlithe.computer.mysql.computer.dictionary.dao.domain.DictionaryDO;
import com.growlithe.computer.mysql.computer.dictionary.dao.domain.DictionaryItemDO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author : Growlithe
 * @Date : 2019/1/11 18:06
 * @Description 字典表dto
 */
public class DictionaryDTO implements Serializable {

    /**
     * 字典表DO
     */
    private DictionaryDO dictionaryDO;
    /**
     * 字典项目表DO
     */
    private List<DictionaryItemDO> dictionaryItemDOList;

    public DictionaryDO getDictionaryDO() {
        return dictionaryDO;
    }

    public void setDictionaryDO(DictionaryDO dictionaryDO) {
        this.dictionaryDO = dictionaryDO;
    }

    public List<DictionaryItemDO> getDictionaryItemDOList() {
        return dictionaryItemDOList;
    }

    public void setDictionaryItemDOList(List<DictionaryItemDO> dictionaryItemDOList) {
        this.dictionaryItemDOList = dictionaryItemDOList;
    }

    @Override
    public String toString() {
        return "DictionaryDTO{" +
                "dictionaryDO=" + dictionaryDO +
                ", dictionaryItemDOList=" + dictionaryItemDOList +
                '}';
    }
}