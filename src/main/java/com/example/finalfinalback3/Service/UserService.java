package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.Entity.UserEntity;
import com.example.finalfinalback3.Exceptions.UserAlreadyExistsException;
import com.example.finalfinalback3.Exceptions.UserNotFoundException;
import com.example.finalfinalback3.Model.User;
import com.example.finalfinalback3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistsException {
            if (userRepo.findByLogin(user.getLogin()) != null) {
                throw new UserAlreadyExistsException("Пользователь с таким логином уже существует!");
            }
            return userRepo.save(user);
    }

    //Спросить насчёт использования Optional
    public User getSingleUser(Integer id) throws UserNotFoundException{
        if (userRepo.findById(id).get() == null) {
            throw new UserNotFoundException("Пользователя с таким id не существует!");
        }
        return User.toModel(userRepo.findById(id).get());
    }

    public Integer deleteUser(Integer id){
        userRepo.deleteById(id);
        return id;
    }
}
