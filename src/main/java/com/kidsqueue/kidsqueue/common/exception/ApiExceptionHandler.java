package com.kidsqueue.kidsqueue.common.exception;

import com.kidsqueue.kidsqueue.common.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

	@ExceptionHandler(value = ApiException.class)
	public ResponseEntity<Api> apiExceptionHandler(ApiException apiException) {

		log.error("", apiException);

		return ResponseEntity
			.status(apiException.getHttpStatus())
			.body(Api.error(apiException.getMessage()));
	}


}
