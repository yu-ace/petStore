package com.zzz.petstore.dao;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.json.JSONUtil;
import com.zzz.petstore.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    public static void save(List<Order> orderList){
        String str = JSONUtil.toJsonStr(orderList);
        FileWriter fileWriter = new FileWriter(FileUtil.getUserHomePath() + "/petStore/orderData.dat");
        fileWriter.write(str);
    }

    public static List<Order> load(){
        try{
            FileReader fileReader = new FileReader(FileUtil.getUserHomePath() + "/petStore/orderData.dat");
            String str = fileReader.readString();
            List<Order> orderList =JSONUtil.toList(str, Order.class);
            return orderList;
        }catch(Exception e){
            return new ArrayList<>();
        }
    }
}
