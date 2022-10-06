package com.zzz.petstore.dao;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.json.JSONUtil;
import com.zzz.petstore.model.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityDao {
    public static void save(List<Activity> activityList){
        String str = JSONUtil.toJsonStr(activityList);
        FileWriter fileWriter = new FileWriter(FileUtil.getUserHomePath() + "/petStore/activityData.dat");
        fileWriter.write(str);
    }

    public static List<Activity> load(){
        try{
            FileReader fileReader = new FileReader(FileUtil.getUserHomePath() + "/petStore/activityData.dat");
            String str = fileReader.readString();
            List<Activity> activityList =JSONUtil.toList(str, Activity.class);
            return activityList;
        }catch(Exception e){
            return new ArrayList<>();
        }
    }
}
