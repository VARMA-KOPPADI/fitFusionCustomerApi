package com.fitFusion.customerApi.excepton;

import com.fitFusion.customerApi.apiResponse.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ApiResponse<String>> nullExceptionHandle(NullPointerException e) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setStatus(500);
        response.setMessage(e.getLocalizedMessage());
        response.setData(null);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApiResponse<String>> runtimeExceptionHandle(RuntimeException e) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setStatus(500);
        response.setMessage(e.getLocalizedMessage());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

