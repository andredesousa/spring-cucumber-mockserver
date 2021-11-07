package app.service;

import app.dto.UserDto;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Service
public class UserService {

    @Value("${api.address}")
    private String address;

    private RestTemplate api = new RestTemplate();

    private String resource = "/user/";

    /**
     * Gets a list of users.
     * @return A list of users.
     */
    public List<UserDto> findAll() {
        return Arrays.asList(api.getForEntity(address + resource, UserDto[].class).getBody());
    }

    /**
     * Gets a user by id.
     * @param id - The id of the user.
     * @return The user.
     */
    public UserDto findById(Integer id) {
        return api.getForEntity(address + resource + id, UserDto.class).getBody();
    }

    /**
     * Creates a user.
     * @param user - The user to insert.
     * @return The inserted user.
     */
    public UserDto create(UserDto user) {
        return api.postForEntity(address + resource, user, UserDto.class).getBody();
    }

    /**
     * Updates a user.
     * @param user - The user to update.
     */
    public void update(Integer id, UserDto user) {
        api.put(address + resource + id, user);
    }

    /**
     * Deletes a user by id.
     * @param id - The id of the user to delete.
     */
    public void delete(Integer id) {
        api.delete(address + resource + id);
    }
}
