package com.joucode.campus_x_jobs.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Response<T> {

    private Integer code;

    private String status;

    private T data;

}
