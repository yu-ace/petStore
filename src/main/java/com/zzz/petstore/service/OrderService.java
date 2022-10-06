package com.zzz.petstore.service;

import com.zzz.petstore.dao.OrderDao;
import com.zzz.petstore.model.Order;

import java.util.Date;
import java.util.List;

public class OrderService {
    static List<Order> orderList = OrderDao.load();

    public static void newOrder(String userid,int petId,double amount){
        Order order = new Order();
        order.setUserid(userid);
        order.setPetId(petId);
        order.setSaleTime(new Date());
        order.setAmount(amount);
        orderList.add(order);
        OrderDao.save(orderList);
    }

    public static List<Order> getOrderList(){
        return orderList;
    }


}
