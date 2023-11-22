package com.example.finalfinalback3.Controller;

import com.example.finalfinalback3.Exceptions.UserAlreadyExistsException;
import com.example.finalfinalback3.Exceptions.UserNotFoundException;
import com.example.finalfinalback3.Repository.UserRepository;
import com.example.finalfinalback3.Service.UserService;
import com.example.finalfinalback3.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user){
        try{
            userService.registration(user);
            return ResponseEntity.ok("Пользователь сохранён ;)");
        }
        catch (UserAlreadyExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Технические шоколадки");
        }
    }


    @GetMapping
    public ResponseEntity getSingleUser(@RequestParam Integer id){
        try{
            return ResponseEntity.ok(userService.getSingleUser(id));
        }
        catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Технические шоколаки");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(userService.deleteUser(id));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Технические шоколаки");
        }
    }

}
