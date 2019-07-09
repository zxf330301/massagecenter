package com.zxf.massagecenter.common;

import lombok.Data;

/**
 * 统一返回
 * @param <T>
 */
@Data
public class MyResponse {
    private String code;
    private String msg;
    private Object data;

    public static MyResponse ok(Object t){
        MyResponse myResponse = new MyResponse();
        myResponse.setCode("0");
        myResponse.setMsg("success");
        myResponse.setData(t);
        return myResponse;
    }

    public static MyResponse error(){
        MyResponse myResponse = new MyResponse();
        myResponse.setCode("0");
        return myResponse;
    }

}
