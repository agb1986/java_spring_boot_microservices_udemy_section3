package course.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import course.exceptions.UserExceptions;
import course.models.Post;
import course.models.User;
import course.services.PostsService;
import course.services.UsersService;

@RestController
public class PostsController {
    @Autowired
    UsersService usersService;

    @Autowired
    PostsService postsService;

    @GetMapping(path = "/users/{id}/posts")
    public ResponseEntity<Object> getUserPosts(@PathVariable Integer id) {
        Optional<User> user = usersService.getUser(id);

        if (!user.isPresent()) {
            throw new UserExceptions().new UserNotFoundException("USER NOT FOUND: " + id);
        }
        return new ResponseEntity<>(user.get().getPosts(), HttpStatus.OK);
    }

    @PostMapping(path = "/users/{id}/posts")
    public ResponseEntity<Object> createUserPost(@PathVariable Integer id, @RequestBody Post post) {
        Optional<User> userOptional = usersService.getUser(id);

        if (!userOptional.isPresent()) {
            throw new UserExceptions().new UserNotFoundException("USER NOT FOUND: " + id);
        }

        User user = userOptional.get();
        post.setUser(user);
        postsService.createUser(post);

        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping(path = "/users/{id}/posts/{postId}")
    public ResponseEntity<Object> getUserPostDetails(@PathVariable Integer id, @PathVariable Integer postId) {
        return new ResponseEntity<>("USER: " + id + " POST: " + postId, HttpStatus.OK);
    }
}
