package br.com.dsi.javajwttoken.infra.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseDto<T>(
        T data,

        @JsonProperty("timestamp") String dateTimeNow) {
    public ResponseDto(T data, String dateTimeNow) {
        this.data = data;
        if (dateTimeNow == null) {
            dateTimeNow = LocalDate.now().toString();
        }
        this.dateTimeNow = dateTimeNow;
    }

}
