package ru.edu.lecture3;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class UserStorageImpl implements UserStorage{

    private List<User> userStorage;

    @Override
    public User getUserByLogin(String login) {
        if (login == null || login.isEmpty())
            throw new IllegalArgumentException("login не указан");
        for (User curUser : userStorage){
            if (login.equals(curUser.getLogin())){
                return curUser;
            }
        }
        throw  new RuntimeException("пользователь не найден");
    }

    @Override
    public User put(User user) {
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new RuntimeException("login отсутствует");
        }
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new RuntimeException("имя отсутствует");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new RuntimeException("фамилия отсутствует");
        }
        if (user.getBirthDate() == null ) {
            throw new RuntimeException("дата рождения не указана");
        }
        if (user.getGender() == null) {
            throw new RuntimeException("пол не указан");
        }
        userStorage.add(user);
        return null;
    }

    @Override
    public User remove(String login) {
        if (login == null || login.isEmpty())
            throw new IllegalArgumentException("login не указан");
        for (User curUser : userStorage){
            if (login.equals(curUser.getLogin())){
                userStorage.remove(curUser);
                return curUser;
            }
        }
        throw  new RuntimeException("пользователь не найден");
    }

    @Override
    public List<User> getAllUsers() {
        return userStorage;
    }

    @Override
    public List<User> getAllUsersByGender(Gender gender) {
        if (gender == null) throw new IllegalArgumentException();
        List<User> usersByGender = new ArrayList<>();
        for (User curUser : userStorage){
            if (gender == curUser.getGender()){
                usersByGender.add(curUser);
            }
        }
        return usersByGender ;
    }

    @Override
    public int getUserAge(String login) {
        if (login == null || login.isEmpty())
            throw new IllegalArgumentException("login не указан");
        for (User curUser : userStorage){
            if (login.equals(curUser.getLogin())){
                Period period = Period.between(curUser.getBirthDate(),LocalDate.now());
                return period.getYears();
            }
        }
        throw  new RuntimeException("пользователь не найден");
    }
}
