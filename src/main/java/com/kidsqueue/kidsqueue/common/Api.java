package com.kidsqueue.kidsqueue.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Api<T> {

    public static final String SUCCESS_STATUS = "success";
    public static final String FAIL_STATUS = "fail";
    public static final String ERROR_STATUS = "error";

    private String status;
    private String message;
    private T data;

    private Pagination pagination;


    // 응답 성공시 Api 객체 만들어주는 메서드
    public static <T> Api<T> ok(T data, String message) {
        return Api.<T>builder()
            .status(Api.SUCCESS_STATUS)
            .message(message)
            .data(data)
            .build();
    }

    // 응답 실패시 Api 객체 만들어주는 메서드
    public static Api error(String message) {
        return Api.builder()
            .status(Api.ERROR_STATUS)
            .message(message)
            .build();
    }

}
