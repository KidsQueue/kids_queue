package com.kidsqueue.kidsqueue.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    public static final String SUCCESS_STATUS = "success";
    public static final String FAIL_STATUS = "fail";
    public static final String ERROR_STATUS = "error";
    public static final String NOT_FOUND = "not found";

    private String status;
    private String message;
    private T data;

    private Pagination pagination;

}
