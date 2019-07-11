package com.growlithe.computer.mysql.computer.dictionary.dao.mapper;


import com.growlithe.computer.mysql.computer.dictionary.dao.domain.DictionaryDO;

public interface DictionaryMapper {

    /**
     * 保存字典数据
     * @param dictionaryDO
     * @return
     */
    int save(DictionaryDO dictionaryDO);
}