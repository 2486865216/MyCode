package com.example.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/3/19  13:09
 */
public class Message {
    private Integer code;
    private String message;
    private Map<String, Object> map = new HashMap<>();

    public static Message success(){
        Message result = new Message();
        result.setCode(100);
        result.setMessage("处理成功");
        return result;
    }
    public static Message failure(){
        Message result = new Message();
        result.setCode(200);
        result.setMessage("处理失败");
        return result;
    }
    public Message add(String name, Object object){
        this.getMap().put(name, object);
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Message{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", map=" + map +
                '}';
    }
}
