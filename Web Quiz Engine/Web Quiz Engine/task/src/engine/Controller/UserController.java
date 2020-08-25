package engine.Controller;


import engine.Model.User;
import engine.Repository.UserRepository;
import engine.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping(path="/api/register", consumes = "application/json", produces = "application/json")
    public User registerUser(@Valid @RequestBody User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ResponseStatusException(BAD_REQUEST, "User exists!");
        } else {
            userService.registerUser(user);
        }
        return user;
    }
}
