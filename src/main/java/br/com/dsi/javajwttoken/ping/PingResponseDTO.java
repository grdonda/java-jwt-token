package br.com.dsi.javajwttoken.ping;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PingResponseDTO(
        String message,

        @JsonProperty("date_time_now")
        String dateTimeNow) {
}
