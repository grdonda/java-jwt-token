package br.com.dsi.javajwttoken.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dsi.javajwttoken.domain.ping.dto.PingResponseDTO;
import br.com.dsi.javajwttoken.infra.response.ResponseDto;

@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping
    public ResponseEntity<ResponseDto<PingResponseDTO>> ping() {
        
        LocalDateTime dateTimeNow = new Date()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        PingResponseDTO pingResponseDTO = new PingResponseDTO("pong");

        ResponseDto<PingResponseDTO> response = new ResponseDto<>(pingResponseDTO, dateTimeNow.toString());
        return ResponseEntity.ok(response);
    }
}
