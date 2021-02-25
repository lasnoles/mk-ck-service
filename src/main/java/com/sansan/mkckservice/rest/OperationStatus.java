package com.sansan.mkckservice.rest;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OperationStatus {
    public enum Status {Success, Fail}
    private Status status;
    private String errorMsg;

    public static OperationStatus buildSuccess(){
        return OperationStatus.builder().status(OperationStatus.Status.Success).build();
    }

    public static OperationStatus buildFail(String errorMsg) {
        return OperationStatus.builder().status(OperationStatus.Status.Fail).errorMsg(errorMsg).build();
    }
}
