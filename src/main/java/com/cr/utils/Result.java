package com.cr.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 全局统一返回结果类
 */
@Data
public class Result<T> {
    private Boolean success;//是否成功
    private int code;//状态码
    private String message;//返回消息
    private T data;//返回数据
    /**
     * 私有化构造方法，禁止在其它类创建对象
     */
    private Result(){}
    /**
     * 成功执行，不返回数据
     * @return
     */
    public static<T> Result<T> ok(){
        Result<T> result = new Result<T>();
        result.setSuccess(true);
        result.setCode(HttpStatus.OK.value());
        result.setMessage("执行成功");
        return result;
    }
    /**
     * 成功执行，并返回数据
     * @param data
     * @param <T>
     * @return
     * static<T> 是 Java 泛型中用来定义静态泛型方法的关键字。
     * 其中，T 是类型参数，表示可以在方法声明中使用未知的数据类型，(也就是说在静态方法中如果需要使用到泛型，则必须写成 static<T>)
     * 并在实际调用该方法时传入具体的类型。static 关键字表示该方法是一个静态方法，
     * 可以直接通过类名调用，无需创建对象实例。
     */
    public static<T> Result<T> ok(T data){
        Result<T> result = new Result<T>();
        result.setSuccess(true);
        result.setCode(HttpStatus.OK.value());
        result.setMessage("执行成功");
        result.setData(data);
        return result;
    }
    /**
     * 失败
     * @return
     */
    public static<T> Result<T> error(){
        Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.setMessage("执行失败");
        return result;
    }
    /**
     * 设置是否成功
     * @param success
     * @return
     */
    public Result<T> success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    /**
     * 设置状态码
     * @param code
     * @return
     */
    public Result<T> code(int code){
        this.setCode(code);
        return this;
    }
    /**
     * 设置返回消息
     * @param message
     * @return
     */
    public Result<T> message(String message){
        this.setMessage(message);
        return this;
    }
//    /**
//     * 是否存在
//     * @return
//     */
//    public static<T> Result<T> exist(){
//        Result<T> result = new Result<T>();
//        result.setSuccess(true);
//        result.setCode(ResultCode.SUCCESS);
//        result.setMessage("执行成功");
//        return result;
//    }
}
