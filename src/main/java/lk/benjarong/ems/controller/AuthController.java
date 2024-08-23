package lk.benjarong.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lk.benjarong.ems.dto.AuthResponse;
import lk.benjarong.ems.dto.LoginDto;
import lk.benjarong.ems.entity.UserEntity;
import lk.benjarong.ems.repository.UserRepository;
import lk.benjarong.ems.security.UserDetailsImpl;
import lk.benjarong.ems.security.jwt.JwtUtils;
import lk.benjarong.ems.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;
    
    @PostMapping("/auth/superadmin/login")
    public ResponseEntity<?> superAdminLogin(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();
        return ResponseEntity.ok().body(new AuthResponse(token, userId));
    }

    @PostMapping("/auth/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();
        return ResponseEntity.ok().body(new AuthResponse(token, userId));
    }

    @PostMapping("/auth/superadmin/register")
    public ResponseEntity<?> superAdminRegister(@RequestBody UserEntity userEntity) {
        if (userRepository.existsByUsername(userEntity.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already in use");
        }
        if (userRepository.existsByEmail(userEntity.getEmail())) {
            return ResponseEntity.badRequest().body("Email address is already in use");
        }
        return ResponseEntity.ok(userService.createSuperAdmin(userEntity));
    }

    @PostMapping("/auth/admin/register")
    public ResponseEntity<?> adminRegister(@RequestBody UserEntity userEntity) {
        if (userRepository.existsByUsername(userEntity.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already in use");
        }
        if (userRepository.existsByEmail(userEntity.getEmail())) {
            return ResponseEntity.badRequest().body("Email address is already in use");
        }
        return ResponseEntity.ok(userService.createAdmin(userEntity));
    }
    
}
