package com.growlithe.computer.mysql.computer.book.dao.mapper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.growlithe.computer.mysql.computer.book.dao.domain.BookDO;
import com.growlithe.computer.mysql.computer.book.service.enums.BookClassEnum;
import com.growlithe.computer.mysql.computer.book.service.enums.BookFormatEnum;
import com.growlithe.computer.mysql.computer.book.service.enums.BookSubordinateClassEnum;
import com.growlithe.computer.mysql.enums.CapacityUnitEnum;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author : Growlithe
 * @Date : 2018/5/19 11:33
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void listAllBookDO() {
        var bookList = bookMapper.listAllBookDO();
        Assert.assertNotNull(bookList);
    }

    @Test
    public void saveBatch() {
        var createBy = "WANGXin";

        var bookName = "杨锦麟这家伙";
//        var bookAuthor = "柏杨";
        var bookClass = BookClassEnum.LITERATURE.getCode();
        var bookSubordinateClass = BookSubordinateClassEnum.LITERATURE_CHINESE_NOW.getCode();

        var bookFormat = BookFormatEnum.PDF.getCode();
        var bookCapacity = new BigDecimal("1.02");
        var capacityUnit = CapacityUnitEnum.MB.getCode();

        var book = new BookDO();
        book.setBookName(bookName);
//        book.setBookAuthor(bookAuthor);
        book.setBookClass(bookClass);
        book.setBookSubordinateClass(bookSubordinateClass);

        book.setBookFormat(bookFormat);
        book.setBookCapacity(bookCapacity);
        book.setCapacityUnit(capacityUnit);
        book.setCreateBy(createBy);

        var bookList = Lists.newArrayList(book);

        var count = bookMapper.saveBatch(bookList);
        Assert.assertNotNull(count);
    }
}