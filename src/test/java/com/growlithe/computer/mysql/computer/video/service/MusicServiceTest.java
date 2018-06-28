package com.growlithe.computer.mysql.computer.video.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.growlithe.computer.common.CandyResult;
import com.growlithe.computer.mysql.computer.video.dao.domain.MusicDO;
import com.growlithe.computer.mysql.computer.video.dao.domain.VideoDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author : Growlithe
 * @Date : 2018/5/21 23:30
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MusicServiceTest {

    @Autowired
    private MusicService musicService;

    @Test
    public void saveBatchTest() {

        String jsonArray = "[" +
                "        {\n" +
                "            \"name\":\"零之使魔 三美姬的轮舞\"," +
                "            \"englishName\":\" Zero no Tsukaima Princess no Rondo\"," +
                "            \"capacity\":17.3," +
                "            \"capacityUnit\":\"G\"," +
                "            \"format\":\"mkv\"," +
                "            \"language\":\"japanese\"," +
                "            \"quality\":\"1080P\"," +
                "            \"videoClass\":\"cartoon\"," +
                "            \"series\":\"zero_no_tsukaima\"," +
                "            \"status\":1, " +
                "            \"createBy\":WANGXin," +
                "            \"createTime\":\"May 19, 2018, 8:45:04 PM\"\n" +
                "        },{\n" +
                "            \"name\":\"零之使魔 F\"," +
                "            \"englishName\":\"Zero no Tsukaima F\"," +
                "            \"capacity\":17.8," +
                "            \"capacityUnit\":\"G\"," +
//                 "            \"author\":\"吴培元\"," +
                "            \"format\":\"mkv\"," +
                "            \"language\":\"japanese\"," +
                "            \"quality\":\"1080P\"," +
                "            \"videoClass\":\"cartoon\"," +
                "            \"series\":\"zero_no_tsukaima\"," +
                "            \"status\":1, " +
                "            \"createBy\":WANGXin," +
                "            \"createTime\":\"May 19, 2018, 8:45:04 PM\"\n" +
                "        }]";

        Gson gson = new Gson();
        List<MusicDO> musicDOList = gson.fromJson(jsonArray, new TypeToken<List<MusicDO>>() {}.getType());

        CandyResult<Void> candyResult = musicService.saveBatch(musicDOList);
        Assert.assertTrue(candyResult.isSuccess());
    }
}