package com.gym.common;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private final boolean success;
    private final int code;
    private final String message;
    private final long timestamp;
    private final Map<String, Object> data = new LinkedHashMap<>();

    private ApiResponse(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public static ApiResponse ok() {
        return new ApiResponse(true, ResultCode.SUCCESS, "ok");
    }

    public static ApiResponse fail(int code, String message) {
        return new ApiResponse(false, code, message);
    }

    public ApiResponse add(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @JsonAnyGetter
    public Map<String, Object> getData() {
        return data;
    }
}
