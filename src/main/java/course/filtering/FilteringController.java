package course.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
    
    @GetMapping(path = "/filtering")
    public SomeBean retriveSomeBean() {
        return new SomeBean("hello", "not", "world");
    }
}
