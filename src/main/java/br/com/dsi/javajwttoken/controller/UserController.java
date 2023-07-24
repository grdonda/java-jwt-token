package br.com.dsi.javajwttoken.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dsi.javajwttoken.domain.user.dto.UserResponseDto;
import br.com.dsi.javajwttoken.domain.user.entities.UserEntity;
import br.com.dsi.javajwttoken.domain.user.service.UserService;
import br.com.dsi.javajwttoken.infra.response.ResponseDto;

@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public ResponseEntity<ResponseDto<UserResponseDto>> getInfo() {
        log.info("Started UserController::getInfo()");

        UserEntity responseUserEntity = userService.getUserData();
        log.info("responseUserEntity: {}", responseUserEntity);

        UserResponseDto responseUserDto = new UserResponseDto();
        responseUserDto.setUsername(responseUserEntity.getUsername());
        log.info("responseUserDto: {}", responseUserDto);

        ResponseDto<UserResponseDto> responseDto = new ResponseDto<UserResponseDto>(responseUserDto, null);
        log.info("responseDto: {}", responseDto);
        
        return ResponseEntity.ok(responseDto);
    }
}
