package br.com.dsi.javajwttoken.ping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping
    public ResponseEntity<PingResponseDTO> ping() {
        String dateTimeNow = "2023-06-01T10:00:00";
        PingResponseDTO pingResponseDTO = new PingResponseDTO("pong", dateTimeNow);
        return ResponseEntity.ok(pingResponseDTO);
    }
}
