package com.ericpinto.publicationsapp.domain.service.exceptions;

public class ObjectNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1l;

    public ObjectNotFoundException(String msg){
        super(msg);
    }
}
