package br.com.dsi.javajwttoken.domain.user.dto;

import lombok.Data;

@Data
public class UserResponseDto {
        private String username;
        private String password;

        public UserResponseDto() {
                this.password = "*********";
        }

        public UserResponseDto(String username) {
                this.username = username;
                this.password = "*********";
        }

        public UserResponseDto(String username, String password) {
                this.username = username;
                this.password = password;
        }
}
