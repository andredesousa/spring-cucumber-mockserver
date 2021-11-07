package app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.dto.UserDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

@DisplayName("UserService")
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    RestTemplate api;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void beforeEach() {
        ReflectionTestUtils.setField(userService, "address", "localhost");
    }

    @Test
    @DisplayName("#findAll returns an array of users")
    void findAll() {
        ResponseEntity<UserDto[]> response = new ResponseEntity<UserDto[]>(new UserDto[0], HttpStatus.OK);
        when(api.getForEntity("localhost/user/", UserDto[].class)).thenReturn(response);

        assertThat(userService.findAll()).isEqualTo(List.of());
    }

    @Test
    @DisplayName("#findById returns a user")
    void findById() {
        UserDto user = new UserDto();
        ResponseEntity<UserDto> response = new ResponseEntity<UserDto>(user, HttpStatus.OK);
        when(api.getForEntity("localhost/user/1", UserDto.class)).thenReturn(response);

        assertThat(userService.findById(1)).isEqualTo(user);
    }

    @Test
    @DisplayName("#create returns a new user")
    void createUser() {
        UserDto user = new UserDto();
        ResponseEntity<UserDto> response = new ResponseEntity<UserDto>(user, HttpStatus.OK);
        when(api.postForEntity("localhost/user/", user, UserDto.class)).thenReturn(response);

        assertThat(userService.create(user)).isEqualTo(user);
    }

    @Test
    @DisplayName("#update calls api #put method")
    void updateUser() {
        UserDto user = new UserDto();
        userService.update(1, user);

        verify(api).put("localhost/user/1", user);
    }

    @Test
    @DisplayName("#delete calls api #delete method")
    void deleteUser() {
        userService.delete(1);

        verify(api).delete("localhost/user/1");
    }
}
