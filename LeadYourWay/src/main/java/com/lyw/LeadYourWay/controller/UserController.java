package com.lyw.LeadYourWay.controller;

import com.lyw.LeadYourWay.exception.ResourceNotFoundException;
import com.lyw.LeadYourWay.exception.ValidationException;
import com.lyw.LeadYourWay.model.Bicycle;
import com.lyw.LeadYourWay.model.User;
import com.lyw.LeadYourWay.repository.UserRepository;
import com.lyw.LeadYourWay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leadyourway/v1")
public class UserController {
    @Autowired
    private UserService userService;

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // URL: http://localhost:8080/api/leadyourway/v1/users
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/leadyourway/v1/users/{userId}
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "userId") Long userId) {
        existsUserByUserId(userId);
        return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/leadyourway/v1/users/filterByEmail
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/users/filterByEmail")
    public ResponseEntity<List<User>> getAllUsersByEmail(@RequestParam(name = "email") String email) {
        existsUserByEmail(email);
        return new ResponseEntity<List<User>>(userRepository.findByUserEmail(email), HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/leadyourway/v1/login
    // Method: POST
    @Transactional(readOnly = true)
    @PostMapping("/login")
    public ResponseEntity<Long> getUserIdByEmailAndPassword(@RequestBody User user) {
        User foundUser = userRepository.findByUserEmailAndUserPassword(user.getUserEmail(), user.getUserPassword());
        if (foundUser != null) {
            return new ResponseEntity<Long>(foundUser.getId(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // URL: http://localhost:8080/api/leadyourway/v1/users
    // Method: POST
    @Transactional
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        validateUser(user);
        existsUserByEmail(user);
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    // URL: http://localhost:8080/api/leadyourway/v1/users/{userId}
    // Method: PUT
    @Transactional
    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable(name = "userId") Long userId, @RequestBody User user) {
        existsUserByUserId(userId);
        validateUser(user);
        user.setId(userId);
        return new ResponseEntity<User>(ifDifferentOrEmptyUpdate(user), HttpStatus.OK);
        //return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/leadyourway/v1/users/{userId}
    // Method: DELETE
    @Transactional
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "userId") Long userId) {
        existsUserByUserId(userId);
        userService.deleteUser(userId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    private void validateUser(User user) {
        if (user.getUserFirstName() == null || user.getUserFirstName().isEmpty()) {
            throw new ValidationException("El nombre del usuario debe ser obligatorio");
        }
        if (user.getUserFirstName().length() > 50) {
            throw new ValidationException("El nombre del usuario no debe exceder los 50 caracteres");
        }
        if (user.getUserLastName() == null || user.getUserLastName().isEmpty()) {
            throw new ValidationException("El apellido del usuario debe ser obligatorio");
        }
        if (user.getUserLastName().length() > 50) {
            throw new ValidationException("El apellido del usuario no debe exceder los 50 caracteres");
        }
        if (user.getUserEmail() == null || user.getUserEmail().isEmpty()) {
            throw new ValidationException("El email del usuario debe ser obligatorio");
        }
        if (user.getUserEmail().length() > 50) {
            throw new ValidationException("El email del usuario no debe exceder los 50 caracteres");
        }
        if (user.getUserPassword() == null || user.getUserPassword().isEmpty()) {
            throw new ValidationException("La contraseña del usuario debe ser obligatorio");
        }
        if (user.getUserPassword().length() > 100) {
            throw new ValidationException("La contraseña del usuario no debe exceder los 100 caracteres");
        }

    }

    private void existsUserByEmail(User user) {
        if (userRepository.existsByUserEmail(user.getUserEmail())) {
            throw new ValidationException("Ya existe un usuario con el email " + user.getUserEmail());
        }
    }

    private void existsUserByEmail(String email) {
        if (!userRepository.existsByUserEmail(email)) {
            throw new ResourceNotFoundException("No existe un usuario con el email " + email);
        }
    }

    private void existsUserByUserId(Long userId) {
        if (userService.getUserById(userId) == null) {
            throw new ResourceNotFoundException("No existe un usuario con el id " + userId);
        }
    }

    private User ifDifferentOrEmptyUpdate(User user){
        return userRepository.findById(user.getId()).map(userToUpdate -> {
            if (user.getUserFirstName() != null && !user.getUserFirstName().isEmpty() && !user.getUserFirstName().equals(userToUpdate.getUserFirstName())) {
                userToUpdate.setUserFirstName(user.getUserFirstName());
            }
            if (user.getUserLastName() != null && !user.getUserLastName().isEmpty() && !user.getUserLastName().equals(userToUpdate.getUserLastName())) {
                userToUpdate.setUserLastName(user.getUserLastName());
            }
            if (user.getUserEmail() != null && !user.getUserEmail().isEmpty() && !user.getUserEmail().equals(userToUpdate.getUserEmail())) {
                userToUpdate.setUserEmail(user.getUserEmail());
            }
            if (user.getUserPassword() != null && !user.getUserPassword().isEmpty() && !user.getUserPassword().equals(userToUpdate.getUserPassword())) {
                userToUpdate.setUserPassword(user.getUserPassword());
            }
            if (user.getUserBirthDate() != null && !user.getUserBirthDate().equals(userToUpdate.getUserBirthDate())) {
                userToUpdate.setUserBirthDate(user.getUserBirthDate());
            }
            if (user.getUserPhone() != null && !user.getUserPhone().isEmpty() && !user.getUserPhone().equals(userToUpdate.getUserPhone())) {
                userToUpdate.setUserPhone(user.getUserPhone());
            }
            return userService.updateUser(userToUpdate);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + user.getId()));
    }
}
