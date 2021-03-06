package com.growlithe.computer.mysql.computer.book.dao.mapper;

import com.growlithe.computer.mysql.computer.book.dao.domain.BookDO;

import java.util.List;

/**
 * @Author : Growlithe
 * @Date : 2018/5/19 11:32
 * @Description
 */
public interface BookMapper {

    /**
     * 查询所有的图书
     * @return List<BookDO>
     */
    List<BookDO> listAllBookDO();

    /**
     * 保存图书
     * @param bookDOList
     * @return
     */
    Integer saveBatch(List<BookDO> bookDOList);
}
