package lk.benjarong.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.benjarong.ems.dto.UserPwdDto;
import lk.benjarong.ems.entity.UserEntity;

@Service
public interface UserService {

    UserEntity createSuperAdmin(UserEntity userEntity);
    UserEntity createAdmin(UserEntity userEntity);
    UserEntity getUsersById(Long id);
    List<UserEntity> getAllSuperAdmins();
    List<UserEntity> getAllAdmins();
    UserEntity updateSuperAdmin(Long id, UserEntity userEntity);
    UserEntity updateAdmin(Long id, UserEntity userEntity);
    UserEntity changeSuperAdminPassword(Long id, UserPwdDto userPwdDto);
    UserEntity changeAdminPassword(Long id, UserPwdDto userPwdDto);
    
}