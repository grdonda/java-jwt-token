package br.com.dsi.javajwttoken.ping;

import br.com.dsi.javajwttoken.dto.ResponseDTO;
import br.com.dsi.javajwttoken.ping.dto.PingResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping
    public ResponseEntity<ResponseDTO> ping() {
        LocalDateTime dateTimeNow = new Date()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        PingResponseDTO pingResponseDTO = new PingResponseDTO("pong");

        ResponseDTO response = new ResponseDTO(pingResponseDTO, dateTimeNow.toString());
        return ResponseEntity.ok(response);
    }
}
