package com.example.library.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    // su dung validate
    // Validate thuoc tinh firstName
    @Size(min = 3, max = 10, message = "Invalid first Name! (3-10 character)")
    private String firstName;

    @Size(min = 3, max = 10, message = "Invalid last Name! (3-10 character)")
    private String lastName;

    private String userName;

    @Size(min = 5, max = 15, message = "Invalid Password ! (3-10 character)")
    private String password;
    private String passwordRepeat;
}
