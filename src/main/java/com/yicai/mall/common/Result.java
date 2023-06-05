package com.yicai.mall.common;

import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Result {
    private Integer status;
    private Boolean success;
    private String message;
    private Object Data;
    private String time;

    private Result() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        time = sdf.format(date);
    }

    private static Result getOk() {
        Result result = new Result();
        result.setStatus(200);
        result.setSuccess(true);
        result.setMessage("操作成功");
        return result;
    }

    private static Result getError() {
        Result result = new Result();
        result.setSuccess(false);
        return result;
    }

    public static ResponseEntity<Result> ok() {
        return ResponseEntity.ok(getOk());
    }

    public static ResponseEntity<Result> ok(Object data) {
        Result result = getOk();
        result.setData(data);
        return ResponseEntity.ok().body(result);
    }

    public static ResponseEntity<Result> error(Integer code) {
        Result result = getError();
        result.setStatus(code);
        return ResponseEntity.status(code).body(result);
    }

    public static ResponseEntity<Result> error(Integer code, String message) {
        Result result = getError();
        result.setStatus(code);
        result.setMessage(message);
        return ResponseEntity.status(code).body(result);
    }

    public static ResponseEntity<Result> unauthorized() {
        Result result = getError();
        result.setStatus(401);
        result.setMessage("权限不足");
        return ResponseEntity.status(401).body(result);
    }
}
