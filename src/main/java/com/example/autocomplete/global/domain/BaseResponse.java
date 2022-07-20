package com.example.autocomplete.global.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.http.HttpStatus;

public class BaseResponse {
    protected BaseDomain responseResult;
    @JsonIgnore
    protected HttpStatus responseStatus;

    public BaseResponse(BaseDomain responseResult, HttpStatus responseStatus) {
        this.responseResult = responseResult;
        this.responseStatus = responseStatus;
    }

    public BaseResponse(HttpStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    @JsonUnwrapped
    public BaseDomain getResponseResult() {
        return responseResult;
    }

    public void setResponseResult(BaseDomain responseResult) {
        this.responseResult = responseResult;
    }

    public HttpStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(HttpStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
