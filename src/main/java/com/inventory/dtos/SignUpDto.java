package com.inventory.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {

    @NotBlank(message = "firstName name cannot be blank ")
    @Pattern(regexp = "^[\\S]+$", message = "firstName  must not contain any whitespace")
    private String firstName;

    @NotBlank(message = "lastName name cannot be blank ")
    @Pattern(regexp = "^[\\S]+$", message = "lastName  must not contain any whitespace")
    private String lastName;

    @NotBlank(message = "login field cannot be blank ")
    @Pattern(regexp = "^[\\S]+$", message = "login field  must not contain any whitespace")
    private String login;

    @NotBlank(message = "password field cannot be blank ")
    @Pattern(regexp = "^[\\S]+$", message = "password field  must not contain any whitespace")
    private String password;

}
