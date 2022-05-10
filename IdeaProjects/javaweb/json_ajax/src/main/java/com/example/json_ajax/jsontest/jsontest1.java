package com.example.json_ajax.jsontest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import com.example.json_ajax.person.oersonjson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class jsontest1 {
//    javabean与json 的转换
    @Test
    public void test(){
        oersonjson person = new oersonjson(1,"hello");
        Gson gson = new Gson();
        String s = gson.toJson(person);
        System.out.println(s);

        oersonjson oersonjson1 = gson.fromJson(s, oersonjson.class);
        System.out.println(oersonjson1);
    }
    //    list与json 的转换
    @Test
    public void test1(){
        List<oersonjson> list = new ArrayList<>();
        list.add(new oersonjson(1,"hello"));
        list.add(new oersonjson(2,"word"));
        Gson gson = new Gson();
        String s = gson.toJson(list);
        System.out.println(s);
        List list1 = gson.fromJson(s, new personlist().getType());
        System.out.println(list1.get(0));
        System.out.println(list1.get(1));
    }
    //    Map与json 的转换
    @Test
    public void test3(){
        Map<Integer,oersonjson> person = new HashMap<>();
        person.put(1,new oersonjson(1,"hello"));
        person.put(2,new oersonjson(2,"word"));
        Gson gson = new Gson();
        String s = gson.toJson(person);
        System.out.println(s);
//        Map<Integer, oersonjson> o = gson.fromJson(s, new personMap().getType());
        Map<Integer, oersonjson> o = gson.fromJson(s, new TypeToken<HashMap<Integer,oersonjson>>(){}.getType());
        oersonjson o1 = o.get(1);
        System.out.println(o1);
    }
}
