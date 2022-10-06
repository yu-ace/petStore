package com.zzz.petstore;

import com.zzz.petstore.dao.PetDao;
import com.zzz.petstore.model.Activity;
import com.zzz.petstore.model.Order;
import com.zzz.petstore.model.Pet;
import com.zzz.petstore.service.ActivityService;
import com.zzz.petstore.service.OrderService;
import com.zzz.petstore.service.PetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class PetstoreApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PetstoreApplication.class, args);
    }

     Scanner scanner = new Scanner(System.in);
    @Override
    public void run(String... args) throws Exception {
        while(true){
            printHelp();
            String str = scanner.next();
            if(str.equals("1")){
                List<Pet> petList = PetService.getPetList();
                for(Pet pet : petList){
                    System.out.println(pet.getId() + "\t" + pet.getName() + "\t" + pet.getDescription()
                            + "\t" + pet.getType() + "\t" + pet.getStatus() + "\t" + pet.getPrice());
                }
            }else if(str.equals("2")){
                List<Activity> activityList = ActivityService.getActivityList();
                for(Activity activity : activityList){
                    System.out.println(activity.getId() + "\t" + activity.getName() + "\t"
                            + activity. getDiscount() + "\t" + activity.getType() + "\t"
                            + activity.getStatus());
                }
            }else if(str.equals("3")){
                List<Pet> petList = PetService.getPetList();
                List<Activity> activityList = ActivityService.getActivityList();
                System.out.println("请输入你的姓名");
                String name = scanner.next();
                System.out.println("请输入宠物id");
                int petId = scanner.nextInt();
                for(Pet pet : petList){
                    if(pet.getId() == petId){
                        for(Activity activity : activityList){
                            if(pet.getStatus() == 1){
                                System.out.println("对不清，该宠物已经不存在。");
                            }else if(activity.getStatus() ==0 && activity.getType() == pet.getType()){
                                System.out.println("您需要支付的金额为：" + (pet.getPrice() * activity.getDiscount()));
                                OrderService.newOrder(name,petId,(pet.getPrice() * activity.getDiscount()));
                            }else{
                                System.out.println("您需要支付的金额为：" + pet.getPrice());
                                OrderService.newOrder(name,petId,pet.getPrice());
                            }
                            pet.setStatus(1);
                            PetDao.save(petList);
                        }
                    }
                }
            }else if(str.equals("4")){
                double[] average = PetService.average();
                for(int i = 0;i < average.length;i++){
                    System.out.println(i + "的平均价格为：" + average[i]);
                }
            }else if(str.equals("5")){
                Pet[] max = PetService.max();
                System.out.println(max[0].getPrice());
                System.out.println(max[1].getPrice());
            }else if(str.equals("6")){
                Pet[] min = PetService.min();
                System.out.println(min[0].getPrice());
                System.out.println(min[1].getPrice());
            }else if(str.equals("q")){
                System.exit(0);
            }else if(str.equals("s")){
                petAdmin();
            }
        }

    }

    public void printHelp(){
        System.out.println("欢迎来到宠物商店");
        System.out.println("输入1 查看宠物列表");
        System.out.println("输入2 查看当前活动");
        System.out.println("输入3 购买宠物");
        System.out.println("输入4 查看宠物平均价格");
        System.out.println("输入5 查看宠物最高值");
        System.out.println("输入6 查看宠物最低价");
        System.out.println("输入q 退出系统");
    }

    public void petAdmin(){
        while(true){
            printHelpAdmin();
            String num = scanner.next();
            if(num.equals("1")){
                System.out.println("输入宠物id");
                int id = scanner.nextInt();
                System.out.println("输入宠物姓名");
                String name = scanner.next();
                System.out.println("请输入宠物描述");
                String description = scanner.next();
                System.out.println("请输入宠物类型");
                int type = scanner.nextInt();
                System.out.println("请输入价格");
                int price = scanner.nextInt();
                PetService.newPet(id,name,description,type,price);
            }else if(num.equals("2")){
                List<Order> orderList = OrderService.getOrderList();
                for(Order order : orderList){
                    System.out.println(order.getUserid() + "\t" + order.getPetId() + "\t"
                            + order.getAmount() + "\t" + order.getSaleTime());
                }
            }else if(num.equals("3")){
                System.out.println("输入活动id");
                int id = scanner.nextInt();
                System.out.println("输入活动名称");
                String name = scanner.next();
                System.out.println("输入活动折扣");
                Double discount = scanner.nextDouble();
                System.out.println("输入促销宠物类型");
                int type = scanner.nextInt();
                System.out.println("请输入活动状态");
                int status = scanner.nextInt();
                ActivityService.newActivity(id,name,discount,type,status);
            }else if(num.equals("q")){
                break;
            }
        }
    }

    public void printHelpAdmin(){
        System.out.println("欢迎来到管理员模式：");
        System.out.println("输入1 录入宠物信息");
        System.out.println("输入2 查看所有顾客列表");
        System.out.println("输入3 录入活动信息");
        System.out.println("输入q 退出");
    }

}
