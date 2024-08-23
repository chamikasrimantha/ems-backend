package lk.benjarong.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lk.benjarong.ems.dto.UserPwdDto;
import lk.benjarong.ems.entity.UserEntity;
import lk.benjarong.ems.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/superadmin")
    public UserEntity createSuperAdmin(@RequestBody UserEntity userEntity){
        return userService.createSuperAdmin(userEntity);
    }

    @PostMapping("/admin")
    public UserEntity createAdmin(@RequestBody UserEntity userEntity){
        return userService.createAdmin(userEntity);
    }

    @GetMapping("/users/{id}")
    public UserEntity getUsersById(@PathVariable Long id){
        return userService.getUsersById(id);
    }

    @GetMapping("/superadmin")
    public List<UserEntity> getAllSuperAdmins(){
        return userService.getAllSuperAdmins();
    }

    @GetMapping("/admin")
    public List<UserEntity> getAllAdmins(){
        return userService.getAllAdmins();
    }

    @PutMapping("/superadmin/{id}")
    public UserEntity updateSuperAdmin(@PathVariable Long id, @RequestBody UserEntity userEntity){
        return userService.updateSuperAdmin(id, userEntity);
    }

    @PutMapping("/admin/{id}")
    public UserEntity updateAdmin(@PathVariable Long id, @RequestBody UserEntity userEntity){
        return userService.updateAdmin(id, userEntity);
    }

    @PutMapping("/superadmin/{id}/change-password")
    public UserEntity changeSuperAdminPassword(@PathVariable Long id, @RequestBody UserPwdDto userPwdDto){
        return userService.changeSuperAdminPassword(id, userPwdDto);
    }

    @PutMapping("/admin/{id}/change-password")
    public UserEntity changeAdminPassword(@PathVariable Long id, @RequestBody UserPwdDto userPwdDto){
        return userService.changeAdminPassword(id, userPwdDto);
    }
    
}
