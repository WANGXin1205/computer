package com.growlithe.computer.mysql.computer.video.dao.mapper;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.growlithe.computer.mysql.computer.video.dao.domain.VideoDO;
import com.growlithe.computer.mysql.computer.video.service.emuns.VideoQualityEnum;
import com.growlithe.computer.mysql.computer.video.service.emuns.VideoSeriesEnum;
import com.growlithe.computer.mysql.computer.video.service.emuns.VideoClassEnum;
import com.growlithe.computer.mysql.computer.video.service.emuns.VideoFormatEnum;
import com.growlithe.computer.mysql.enums.CapacityUnitEnum;
import com.growlithe.computer.mysql.enums.LanguageEnum;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

/**
 * @Author : Growlithe
 * @Date : 2018/5/19 18:08
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoMapperTest {

    @Autowired
    private VideoMapper videoMapper;

    @Test
    public void listAllVideoDO() {
        var videoList = videoMapper.listAllVideoDO();
        Assert.assertNotNull(videoList);

        Gson gson = new Gson();
        String json = gson.toJson(videoList);
        System.out.println(json);
//        String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
//        String[] strings = gson.fromJson(jsonArray, String[].class);
//        List<String> stringList = gson.fromJson(jsonArray, new TypeToken<List<String>>() {}.getType());
    }

    @Test
    public void saveBatch() {

//        var createBy = "WANGXin";
//
//        var name = "龙樱";
////        var englishName = "Justice What's The Right Thing To Do Episode";
////        var author = "丁正生";
//        var capacity = new BigDecimal("13.5");
//        var capacityUnit = CapacityUnitEnum.GB.getCode();
//        var format = VideoFormatEnum.MKV.getCode();
//        var language = LanguageEnum.JAPANESE.getCode();
//        var quality = VideoQualityEnum.MIDDLE.getCode();
//        var videoClass = VideoClassEnum.TV.getCode();
////        var series = VideoSeriesEnum.LIE_TO_ME.getCode();
//
//        var video = new VideoDO();
//        video.setName(name);
////        video.setEnglishName(englishName);
////        video.setAuthor(author);
//        video.setCapacity(capacity);
//        video.setCapacityUnit(capacityUnit);
//        video.setFormat(format);
//        video.setLanguage(language);
//        video.setQuanlity(quality);
//        video.setVideoClass(videoClass);
////        video.setSeries(series);
//        video.setCreateBy(createBy);

         String jsonArray = "[" +
                 "        {\n" +
                 "            \"name\":\"Princess Principal\"," +
//                 "            \"englishName\":\"The Killer\"," +
                 "            \"capacity\":17.9," +
                 "            \"capacityUnit\":\"G\"," +
                 "            \"format\":\"mkv\"," +
                 "            \"language\":\"japanese\"," +
                 "            \"quality\":\"1080P\"," +
                 "            \"videoClass\":\"cartoon\"," +
//                 "            \"series\":\"sherlock\"," +
                 "            \"status\":1, " +
                 "            \"createBy\":WANGXin," +
                 "            \"createTime\":\"May 19, 2018, 8:45:04 PM\"\n" +
                 "        },{\n" +
                "            \"name\":\"代号D机关\"," +
//                 "            \"englishName\":\"Downfall\"," +
                "            \"capacity\":11.5," +
                "            \"capacityUnit\":\"G\"," +
                "            \"format\":\"mkv\"," +
                "            \"language\":\"japanese\"," +
                "            \"quality\":\"1080P\"," +
                "            \"videoClass\":\"cartoon\"," +
//                "            \"series\":\"sherlock\"," +
                "            \"status\":1, " +
                "            \"createBy\":WANGXin," +
                "            \"createTime\":\"May 19, 2018, 8:45:04 PM\"\n" +
                "        }]";

        Gson gson = new Gson();
//        VideoDO[] strings = gson.fromJson(jsonArray, VideoDO[].class);
        List<VideoDO> videoList = gson.fromJson(jsonArray, new TypeToken<List<VideoDO>>() {}.getType());

//        var videoList = Lists.newArrayList(video);

        var count = videoMapper.saveBatch(videoList);
        Assert.assertNotNull(count);
    }
}