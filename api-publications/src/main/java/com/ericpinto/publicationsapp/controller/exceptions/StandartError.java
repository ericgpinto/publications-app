package com.ericpinto.publicationsapp.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StandartError implements Serializable {

    private static final long serialVersionUID = 1l;

    private String message;

}
