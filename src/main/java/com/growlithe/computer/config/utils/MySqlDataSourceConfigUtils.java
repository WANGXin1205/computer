package com.growlithe.computer.config.utils;

import com.growlithe.computer.ComputerApplication;
import org.apache.hadoop.mapreduce.v2.app.AppContext;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

/**
 * @Author : Growlithe
 * @Date : 2018/7/5 0:11
 * @Description
 */
public class MySqlDataSourceConfigUtils {

    public static ApplicationContext applicationContext = SpringApplication.run(ComputerApplication.class);

}
