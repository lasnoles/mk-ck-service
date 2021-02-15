package com.sansan.mkckservice.rest;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OperationStatus {
    public enum Status {Success, Fail}
    private Status status;
    private String errorMsg;
}
