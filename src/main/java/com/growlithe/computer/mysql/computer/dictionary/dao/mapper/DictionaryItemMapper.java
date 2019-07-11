package com.growlithe.computer.mysql.computer.dictionary.dao.mapper;


import com.growlithe.computer.mysql.computer.dictionary.dao.domain.DictionaryItemDO;

import java.util.List;

public interface DictionaryItemMapper {

    /**
     * 批量保存
     * @param dictionaryItemDOList
     * @return
     */
    int saveBatch(List<DictionaryItemDO> dictionaryItemDOList);

}