package br.com.dsi.javajwttoken.usuario;

import br.com.dsi.javajwttoken.dto.ResponseDTO;
import br.com.dsi.javajwttoken.usuario.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuariosController {

    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> usersList() {

        List<UsuarioDTO> users = List.of(
                new UsuarioDTO("João", "joao@site.com"),
                new UsuarioDTO("Maria", "maria@site.com")
        );

        LocalDateTime dateTimeNow = new Date()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        ResponseDTO response = new ResponseDTO(users, dateTimeNow.toString());

        return ResponseEntity.ok(response);
    }

}
