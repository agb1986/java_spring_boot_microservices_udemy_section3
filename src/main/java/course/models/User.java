package course.models;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

// import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
// @Entity
public class User {

    private Integer id;
    
    @Size(min=2, message = "Name should be > 2 charaters")
    private String name;
    
    @Past(message = "Date cannot be in future")
    private Date dob;
}
