package com.growlithe.computer.mysql.computer.video.dao.mapper;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.growlithe.computer.mysql.computer.video.dao.domain.VideoDO;
import com.growlithe.computer.mysql.computer.video.service.emuns.VideoClassEnum;
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
public class VideoMapperTest {

    @Autowired
    private VideoMapper videoMapper;

    @Test
    public void listAllVideoDOTest() {
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
    public void listVideoDOTest(){
        var videoList = videoMapper.listVideoDO(VideoClassEnum.CARTOON.getCode());
        Assert.assertNotNull(videoList);
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
                 "            \"name\":\"雪拉比 穿梭时空的相遇\"," +
//                 "            \"englishName\":\" Zero no Tsukaima Princess no Rondo\"," +
                 "            \"capacity\":21.6," +
                 "            \"capacityUnit\":\"G\"," +
                 "            \"format\":\"iso\"," +
                 "            \"language\":\"japanese\"," +
                 "            \"quality\":\"1080P\"," +
                 "            \"videoClass\":\"cartoon\"," +
                 "            \"series\":\"pokemon\"," +
                 "            \"status\":1, " +
                 "            \"createBy\":WANGXin," +
                 "            \"createTime\":\"May 19, 2018, 8:45:04 PM\"\n" +
                 "        },{\n" +
                "            \"name\":\"天空之城\"," +
                 "            \"englishName\":\"Laputa Castle In The Sky\"," +
                "            \"capacity\":32.4," +
                "            \"capacityUnit\":\"G\"," +
//                 "            \"author\":\"吴培元\"," +
                "            \"format\":\"ts\"," +
                "            \"language\":\"japanese\"," +
                "            \"quality\":\"1080P\"," +
                "            \"videoClass\":\"cartoon\"," +
//                "            \"series\":\"zero_no_tsukaima\"," +
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