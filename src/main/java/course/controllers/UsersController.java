package course.controllers;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import course.exceptions.UserExceptions;
import course.models.User;
import course.models.UserDao;

@RestController
public class UsersController {

    @Autowired
    private UserDao userService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "/users-international")
    public String getUsersInternationlised(
            @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("good.morning", null, "DEFAULT MESSAGE", locale);
        // return userService.findAll();
    }

    @GetMapping(path = "/users/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public EntityModel<User> getUser(@PathVariable Integer id) {
        User user = userService.findById(id);

        if (user == null) {
            throw new UserExceptions().new UserNotFoundException("USER NOT FOUND: " + id);
        } else {
            EntityModel<User> model = EntityModel.of(user);
            WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsers());
            model.add(linkToUsers.withRel("all-users"));
            return model;
        }
    }

    @PostMapping(path = "/users", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.save(user);

        if (createdUser == null) {
            throw new UserExceptions().new UserNotCreatedException("USER NOT CREATED" + user);
        } else {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(createdUser.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
        User user = userService.deleteById(id);

        if (user == null) {
            throw new UserExceptions().new UserNotFoundException("USER NOT FOUND: " + id);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }
}
