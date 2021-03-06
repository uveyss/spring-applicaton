package com.lns.n11loanapplication.api.errorHandling.handler;



import com.lns.n11loanapplication.api.errorHandling.configuration.ErrorDto;
import com.lns.n11loanapplication.api.errorHandling.configuration.ErrorMessageLocator;
import com.lns.n11loanapplication.api.errorHandling.response.ApiError;
import com.lns.n11loanapplication.api.errorHandling.response.ApiResponse;
import com.lns.n11loanapplication.api.errorHandling.response.FieldError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;



@RequiredArgsConstructor
@RestControllerAdvice
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RequestExceptionHandler {
    private final ErrorMessageLocator errorMessageLocator;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        ErrorDto errorDto = errorMessageLocator.error("validation.method-argument-not-valid");

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors().stream()
                .map(err -> FieldError.builder().
                        rejectedValue(err.getRejectedValue())
                        .field(err.getField())
                        .message(err.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
        ApiError apiError = ApiError.builder().code(errorDto.getCode()).message(errorDto.getMessage()).fieldErrors(fieldErrors).build();
        return ApiResponse.fail(apiError);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class, MissingPathVariableException.class})
    public ApiResponse handleMethodArgumentNotValid(Exception exception) {
        ErrorDto errorDto = errorMessageLocator.error("validation.fail",exception.getMessage());
        ApiError apiError = ApiError.builder().code(errorDto.getCode()).message(errorDto.getMessage()).build();
        return ApiResponse.fail(apiError);
    }
}