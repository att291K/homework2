import org.junit.Test;
import ru.edu.lecture3.Gender;
import ru.edu.lecture3.User;
import ru.edu.lecture3.UserStorage;

import java.time.LocalDate;

public class UserStorageTests {

    private UserStorage userStorage = null;

    @Test(expected = Exception.class)
    public void userStorageTest_getUserByLogin()  {
        System.out.println("getUserByLogin");
        userStorage.getUserByLogin("qwerty");
    }

    @Test(expected = Exception.class)
    public void userStorageTest_put()  {
        User user = new User("first","Иван", "Иванов", LocalDate.of(1980, 3, 3), Gender.MALE);
        userStorage.put(user);
        System.out.println("put");
        User user1 = new User("second","Иван", "", LocalDate.of(1980, 3, 3), Gender.MALE);
        userStorage.put(user1);
    }

    @Test(expected = Exception.class)
    public void userStorageTest_remove()  {
        userStorage.remove("qwerty");
    }

    @Test(expected = Exception.class)
    public void userStorageTest_getAllUsers()  {
        userStorage.getAllUsers();
    }

    @Test(expected = Exception.class)
    public void userStorageTest_getAllUsersByGender()  {
        Gender gender = null;
        userStorage.getAllUsersByGender(gender);
    }

    @Test(expected = Exception.class)
    public void userStorageTest_getUserAge()  {
        userStorage.getUserAge("asdfg");
    }
}