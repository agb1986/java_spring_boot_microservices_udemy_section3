package course.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostsController {

    @GetMapping(path = "/users/{id}/posts")
    public ResponseEntity<Object> getUserPosts(@PathVariable String id) {
        return new ResponseEntity<>("USER: " + id, HttpStatus.OK);
    }

    @PostMapping(path = "/users/{id}/posts")
    public ResponseEntity<Object> createUserPost(@PathVariable String id) {
        return new ResponseEntity<>("USER: " + id, HttpStatus.OK);
    }

    @GetMapping(path = "/users/{id}/posts/{postId}")
    public ResponseEntity<Object> getUserPostDetails(@PathVariable String id, @PathVariable String postId) {
        return new ResponseEntity<>("USER: " + id + " POST: " + postId, HttpStatus.OK);
    }
}
