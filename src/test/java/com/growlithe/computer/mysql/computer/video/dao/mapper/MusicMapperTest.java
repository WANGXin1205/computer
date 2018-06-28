package com.growlithe.computer.mysql.computer.video.dao.mapper;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.growlithe.computer.mysql.computer.video.dao.domain.VideoDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author : Growlithe
 * @Date : 2018/5/19 18:08
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MusicMapperTest {

    @Autowired
    private MusicMapper musicMapper;

    @Test
    public void listAllMusicDO() {
        var allMusicDO = musicMapper.listAllMusicDO();
        Assert.assertNotNull(allMusicDO);

        Gson gson = new Gson();
        String json = gson.toJson(allMusicDO);
        System.out.println(json);
//        String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
//        String[] strings = gson.fromJson(jsonArray, String[].class);
//        List<String> stringList = gson.fromJson(jsonArray, new TypeToken<List<String>>() {}.getType());
    }

}