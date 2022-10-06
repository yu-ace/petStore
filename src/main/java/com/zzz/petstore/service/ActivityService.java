package com.zzz.petstore.service;

import com.zzz.petstore.dao.ActivityDao;
import com.zzz.petstore.model.Activity;

import java.util.List;

public class ActivityService {
    static List<Activity> activityList = ActivityDao.load();

    public static void newActivity(int id,String name,double discount,int type,int status){
        Activity activity = new Activity();
        activity.setId(id);
        activity.setName(name);
        activity.setDiscount(discount);
        activity.setType(type);
        activity.setStatus(status);
        activityList.add(activity);
        ActivityDao.save(activityList);
    }

    public static List<Activity> getActivityList(){
        return activityList;
    }

}
