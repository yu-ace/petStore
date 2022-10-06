package com.zzz.petstore.service;

import com.zzz.petstore.dao.PetDao;
import com.zzz.petstore.model.Pet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PetService {
    static List<Pet> petList = PetDao.load();

    public static void newPet(int id,String name,String description,int type,double price){
        Pet pet = new Pet();
        pet.setId(id);
        pet.setName(name);
        pet.setDescription(description);
        pet.setType(type);
        pet.setPrice(price);
        pet.setStatus(0);
        petList.add(pet);
        PetDao.save(petList);
    }
    public static List<Pet> getPetList(){
        return petList;
    }

    public static double[] average(){
        double[] sum = new double[2];
        int[] num = new int[2];
        double[] result = new double[2];
        for(Pet pet : petList){
            if(pet.getType() == 0){
                sum[0] = sum[0] + pet.getPrice();
                num[0] = num[0] + 1;
            }
            if(pet.getType() == 1){
                sum[1] = sum[1] + pet.getPrice();
                num[1] = num[1] + 1;
            }
        }
        for(int i = 0;i < 2;i++){
          double avg = sum[i] / num[i];
          result[i] = avg;
        }
        return result;
    }


//
//    public static Pet max(){
//        List<Pet> tmp = new ArrayList<>(petList.size());
//        tmp.addAll(petList);
//        tmp.sort(new Comparator<Pet>() {
//            @Override
//            public int compare(Pet o1, Pet o2) {
//                return Double.compare(o2.getPrice(),o1.getPrice());
//            }
//        });
//        return tmp.get(0);
//    }



    public static Pet[] max(){
        Pet[] pets = new Pet[2];
        for(Pet pet :petList){
            if(pet.getType() ==0){
                if(pets[0] == null){
                    pets[0] = pet;
                }
                if(pets[0].getPrice()<pet.getPrice()){
                    pets[0] = pet;
                }
            }else{
                if(pets[1] == null){
                    pets[1] = pet;
                }
                if(pets[1].getPrice()<pet.getPrice()){
                    pets[1] = pet;
                }
            }
        }
        return pets;
    }

    private static void maxPrice() {
        Pet[] pets = new Pet[2];
        List<Pet> tmp0 = new ArrayList<>(petList.size());
        tmp0.addAll(petList);
        tmp0.sort(new Comparator<Pet>() {
            @Override
            public int compare(Pet o1, Pet o2) {
                return Double.compare(o2.getPrice(),o1.getPrice());
            }
        });
        for (Pet pet : tmp0) {
            if(pet.getType()==0) {
                pets[0] = pet;
                break;
            }
        }
        for (Pet pet : tmp0) {
            if(pet.getType()==1) {
                pets[1] = pet;
                break;
            }
        }
    }

    public static Pet[] min(){
        Pet[] pets = new Pet[2];
        Pet tmp0 = petList.stream()
                .filter(e -> e.getType() == 0)
                .min(Comparator.comparingDouble(Pet::getPrice)).get();

        Pet tmp1 = petList.stream()
                .filter(e->e.getType()==1)
                .min(Comparator.comparingDouble(Pet::getPrice)).get();

        pets[0] = tmp0;
        pets[1] = tmp1;
        return pets;
    }


}
