package com.ericpinto.publicationsapp.controller.exceptions;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorResponse {

    private String message;
    private List<String> details;

    public ErrorResponse(String message, List<String> details){
        super();
        this.message = message;
        this.details = details;
    }
}
