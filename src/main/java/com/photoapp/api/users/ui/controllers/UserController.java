package com.photoapp.api.users.ui.controllers;

import com.photoapp.api.users.service.UserService;
import com.photoapp.api.users.shared.dto.UserDto;
import com.photoapp.api.users.ui.model.CreateUserRequestModel;
import com.photoapp.api.users.ui.model.CreateUserResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin()
public class UserController {

    @Autowired
    private Environment environment;

    @Autowired
    private UserService userService;

    @GetMapping("/status/check")
    public String getStatus() {
        return "Working on .... " + environment.getProperty("local.server.port");
    }

    @GetMapping(value = "/allUsers")
    public ResponseEntity<List> getAllUsers(){
        List<UserDto> users = userService.getUsers();
        return new ResponseEntity<List>(users, HttpStatus.CREATED);
    }

    @PostMapping(value = "/createUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel createUserRequestModel){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(createUserRequestModel, UserDto.class);
        userDto = userService.createUser(userDto);
        CreateUserResponseModel userResponseModel = modelMapper.map(userDto,CreateUserResponseModel.class);
        return new ResponseEntity<CreateUserResponseModel>(userResponseModel, HttpStatus.CREATED);
    }
}
