package br.com.dsi.javajwttoken.domain.user.repository;

import org.springframework.stereotype.Repository;

import br.com.dsi.javajwttoken.domain.user.entities.UserEntity;

@Repository
public class UserRepository {

    public UserEntity getUserByUsername(String username) {
        if (username.equals("grdonda@gmail.com")) {
            return createFakeUser();
        }
        return null;
    }

    private UserEntity createFakeUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("grdonda@gmail.com");
        userEntity.setPassword("$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.");
        return userEntity;
    }

    public UserEntity getUserData() {
        return createFakeUser();
    }
}
