package br.com.dsi.javajwttoken.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ResponseDTO(
        List data,

        @JsonProperty("timestamp")
        String dateTimeNow
) {
    public ResponseDTO(Object data, String dateTimeNow) {
        this(List.of(data), dateTimeNow);
    }
}
