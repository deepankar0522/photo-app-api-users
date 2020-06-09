package com.photoapp.api.users.ui.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {

    @NotNull(message = "First Name cannot be empty")
    @Size(min = 2, max = 20, message = "First Name should be min 2 and max 20 characters")
    private String firstName;

    @NotNull(message = "Last Name cannot be empty")
    @Size(min = 2, max = 20, message = "Last Name should be min 2 and max 20 characters")
    private String lastName;

    @Email(message = "Not a valid Email")
    @NotNull(message = "Email cannot be empty")
    private String email;

    @NotNull(message = "Password cannot be empty")
    @Size(min = 8, max = 20, message = "Password should be min 8 and max 20 characters")
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
