package course.exceptions;

public class UserExceptions {

    public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public class UserNotCreatedException extends RuntimeException {
        public UserNotCreatedException(String message) {
            super(message);
        }
    }
}
