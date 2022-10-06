package com.zzz.petstore.dao;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.json.JSONUtil;
import com.zzz.petstore.model.Pet;

import java.util.ArrayList;
import java.util.List;


public class PetDao {
    public static void save(List<Pet> petList){
        String str = JSONUtil.toJsonStr(petList);
        FileWriter fileWriter = new FileWriter(FileUtil.getUserHomePath() + "/petStore/petData.dat");
        fileWriter.write(str);
    }

    public static List<Pet> load(){
        try{
            FileReader fileReader = new FileReader(FileUtil.getUserHomePath() + "/petStore/petData.dat");
            String str = fileReader.readString();
            List<Pet> petList =JSONUtil.toList(str, Pet.class);
            return petList;
        }catch(Exception e){
            return new ArrayList<>();
        }

    }

}
