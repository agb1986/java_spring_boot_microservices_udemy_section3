package course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import course.models.Post;
import course.repository.PostRepository;

@Service
public class PostsService {
    @Autowired
    PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post createUser(Post post) {
        return postRepository.save(post);
    }
}
