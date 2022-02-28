package org.dale.shiroDemo.model;

import lombok.Data;

/**
 * @author Administrator on 2022/1/24.
 * Description:
 */
@Data
public class RespBean {
    private Integer status;
    private String msg;
    private Object data;

    public static RespBean ok(String msg){
        return new RespBean(200,msg,null);
    }
    public static RespBean ok(String msg,Object data){
        return new RespBean(200,msg,data);
    }
    public static RespBean error(String msg){
        return new RespBean(500,msg,null);
    }
    public static RespBean error(String msg,Object data){
        return new RespBean(500,msg,data);
    }
    public RespBean() {
    }

    public RespBean(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
