package lk.benjarong.ems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lk.benjarong.ems.dto.UserPwdDto;
import lk.benjarong.ems.entity.UserEntity;
import lk.benjarong.ems.entity.UserRole;
import lk.benjarong.ems.repository.UserRepository;
import lk.benjarong.ems.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity createSuperAdmin(UserEntity userEntity) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(userEntity.getUsername());
        newUser.setEmail(userEntity.getEmail());
        newUser.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        newUser.setFullname(userEntity.getFullname());
        newUser.setRole(UserRole.SUPERADMIN);
        return userRepository.save(newUser);
    }

    @Override
    public UserEntity createAdmin(UserEntity userEntity) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(userEntity.getUsername());
        newUser.setEmail(userEntity.getEmail());
        newUser.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        newUser.setFullname(userEntity.getFullname());
        newUser.setRole(UserRole.ADMIN);
        return userRepository.save(newUser);
    }

    @Override
    public UserEntity changeAdminPassword(Long id, UserPwdDto userPwdDto) {
        UserEntity existingAdmin = userRepository.findById(id).orElse(null);
        if (existingAdmin != null) {
            existingAdmin.setPassword(userPwdDto.getPassword());
            return userRepository.save(existingAdmin);
        } else {
            return null;
        }
    }

    @Override
    public UserEntity changeSuperAdminPassword(Long id, UserPwdDto userPwdDto) {
        UserEntity existingSuperAdmin = userRepository.findById(id).orElse(null);
        if (existingSuperAdmin != null) {
            existingSuperAdmin.setPassword(userPwdDto.getPassword());
            return userRepository.save(existingSuperAdmin);
        } else {
            return null;
        }
    }

    @Override
    public List<UserEntity> getAllAdmins() {
        return userRepository.findAdminsByRole(UserRole.ADMIN);
    }

    @Override
    public List<UserEntity> getAllSuperAdmins() {
        return userRepository.findSuperAdminsByRole(UserRole.SUPERADMIN);
    }

    @Override
    public UserEntity updateAdmin(Long id, UserEntity userEntity) {
        UserEntity existingAdmin = userRepository.findById(id).orElse(null);
        if (existingAdmin != null) {
            existingAdmin.setUsername(userEntity.getUsername());
            existingAdmin.setEmail(userEntity.getEmail());
            existingAdmin.setFullname(userEntity.getFullname());
            return userRepository.save(existingAdmin);
        } else {
            return null;
        }
    }

    @Override
    public UserEntity updateSuperAdmin(Long id, UserEntity userEntity) {
        UserEntity existingSuperAdmin = userRepository.findById(id).orElse(null);
        if (existingSuperAdmin != null) {
            existingSuperAdmin.setUsername(userEntity.getUsername());
            existingSuperAdmin.setEmail(userEntity.getEmail());
            existingSuperAdmin.setFullname(userEntity.getFullname());
            return userRepository.save(existingSuperAdmin);
        } else {
            return null;
        }
    }

    @Override
    public UserEntity getUsersById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
