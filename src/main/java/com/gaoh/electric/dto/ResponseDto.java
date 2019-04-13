package com.gaoh.electric.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto<T> {

    public static final Integer SUCCESS_CODE = 0;
    public static final Integer FAILED_CODE = -1;

    private int code;

    private T res;

    private String error;

}
