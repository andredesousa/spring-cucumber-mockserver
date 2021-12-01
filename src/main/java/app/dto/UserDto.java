package app.dto;

import javax.validation.constraints.NotNull;

public class UserDto {

    public Integer id;

    @NotNull
    public String username;

    @NotNull
    public String email;
}
