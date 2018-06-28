package com.growlithe.computer.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by matrixkeymac on 16-10-5.
 * 李哥写的，版权李哥所有
 */
public class ParamUtils {
    /**
     * 序列化参数
     *
     * @param arguments 参数列表
     * @return 序列化后的字符串
     */
    public static String serializable(Object... arguments) {
        try {
            List<String> list = new ArrayList<>();
            int k = 1;
            Gson gs = new Gson();
            for (Object item : arguments) {
                Class cls = item.getClass();
                String typeName = cls.getTypeName();
                String value = gs.toJson(item);
                list.add("参数[" + k + "]:类型-[" + typeName + "],内容-[" + value + "]");
                k++;
            }
            return String.join("####", list);
        } catch (Exception e) {
            return "参数序列化异常";
        }
    }

    /**
     * 序列化参数
     *
     * @param arguments 参数列表,key表示参数名,value表示参数值
     * @return 序列化后的字符串
     */
    public static String serializableMap(Map<String, Object> arguments) {
        try {
            Gson gs = new Gson();
            return gs.toJson(arguments);
        } catch (Exception e) {
            return "参数序列化异常";
        }
    }

    /**
     * 序列化参数
     *
     * @param arguments 参数对象
     * @return 序列化后的字符串
     */
    public static String serializableObject(Object arguments) {
        try {
            Gson gs = new Gson();
            return gs.toJson(arguments);
        } catch (Exception e) {
            return "参数序列化异常";
        }
    }


}
