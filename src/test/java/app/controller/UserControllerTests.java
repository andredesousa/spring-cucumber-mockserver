package app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.dto.UserDto;
import app.service.UserService;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("UserController")
@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Test
    @DisplayName("#findAll returns an array of users")
    void findAll() {
        when(userService.findAll()).thenReturn(new ArrayList<>());

        assertThat(userController.findAll()).isEqualTo(new ArrayList<>());
    }

    @Test
    @DisplayName("#findById returns a user")
    void findById() {
        UserDto user = new UserDto();
        when(userService.findById(1)).thenReturn(user);

        assertThat(userController.findById(1)).isEqualTo(user);
    }

    @Test
    @DisplayName("#create returns a new user")
    void createUser() {
        UserDto user = new UserDto();
        when(userService.create(user)).thenReturn(user);

        assertThat(userController.create(user)).isEqualTo(user);
    }

    @Test
    @DisplayName("#update calls service #update method")
    void updateUser() {
        UserDto user = new UserDto();
        userController.update(1, user);

        verify(userService).update(1, user);
    }

    @Test
    @DisplayName("#delete calls service #delete method")
    void deleteUser() {
        userController.delete(1);

        verify(userService).delete(1);
    }
}
