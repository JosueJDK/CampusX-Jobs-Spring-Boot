package com.joucode.campus_x_jobs.common.mappers;

import com.joucode.campus_x_jobs.common.response.Response;
import org.springframework.http.HttpStatus;

public class ResponseMapper {

    public <T> Response toResponse(T response, HttpStatus httpStatus){
        return new Response(httpStatus.value(), httpStatus.name(), response);
    }

}
