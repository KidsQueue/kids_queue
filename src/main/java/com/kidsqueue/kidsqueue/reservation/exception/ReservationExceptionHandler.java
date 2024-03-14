package com.kidsqueue.kidsqueue.reservation.exception;

import com.kidsqueue.kidsqueue.reservation.model.ApiResponse;
import com.kidsqueue.kidsqueue.reservation.model.ApiResponse.Error;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.kidsqueue.kidsqueue.reservation")
public class ReservationExceptionHandler {
    public ApiResponse returnApiResponseThroughErrorList(List<String> errorList){
        Error error = Error
            .builder()
            .errorList(errorList)
            .build();

        ApiResponse<Object> response = ApiResponse.builder()
            .resultCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
            .resulMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .error(error)
            .build();
        return response;
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ApiResponse validationException(
        MethodArgumentNotValidException exception
    ) {
        log.error("", exception);
        List<String> errorList = exception.getFieldErrors().stream()
            .map(it -> {
                String format = "%s : { %s } 은 %ㄴ";
                String message = String.format(format, it.getField(), it.getRejectedValue(),
                    it.getDefaultMessage());
                return message;
            }).collect(Collectors.toList());
        return returnApiResponseThroughErrorList(errorList);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse illegalArgumentException(IllegalArgumentException exception) {
        log.error("", exception);
        List<String> errorList = new ArrayList<>();
        errorList.add(String.valueOf(exception));

        return returnApiResponseThroughErrorList(errorList);
    }
    @ExceptionHandler(value = {IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse illegalStateException(IllegalStateException exception) {
        log.error("", exception);
        List<String> errorList = new ArrayList<>();
        errorList.add(String.valueOf(exception));

        return returnApiResponseThroughErrorList(errorList);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse errorSql(SQLIntegrityConstraintViolationException exception) {
        log.error("", exception);
        List<String> errorList = new ArrayList<>();
        errorList.add("SQLIntegrityConstraintViolationException 발생");
        return returnApiResponseThroughErrorList(errorList);
    }



}
