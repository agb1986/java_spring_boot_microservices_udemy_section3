package course.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

// @Component
@Deprecated
public class UserDao {
    // private static List<User> users = new ArrayList<>();
    // private static Integer userCount = 3;

    // static {
    //     users.add(new User(1, "Bob Smith", new Date()));
    //     users.add(new User(2, "Joe Bloggs", new Date()));
    //     users.add(new User(3, "Jane Lane", new Date()));
    // }

    // public List<User> findAll() {
    //     return users;
    // }

    // public User findById(Integer id) {
    //     for (User user : users) {
    //         if (user.getId() == id) {
    //             return user;
    //         }
    //     }
    //     return null;
    // }

    // public User save(User user) {
    //     if (user.getId() == null) {
    //         user.setId(++userCount);
    //     }
    //     users.add(user);
    //     return user;
    // }

    // public User deleteById(Integer id) {
    //     Iterator<User> iterator = users.iterator();

    //     while (iterator.hasNext()) {
    //         User user = iterator.next();
    //         if (user.getId().equals(id)) {
    //             iterator.remove();
    //             return user;
    //         }
    //     }
    //     return null;
    // }
}
