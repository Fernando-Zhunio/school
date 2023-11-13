package com.fzhunio.school.adapters;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fzhunio.school.authentication.dto.CreateUserDto;
import com.fzhunio.school.authentication.dto.JwtResponse;
import com.fzhunio.school.authentication.dto.LoginDto;
import com.fzhunio.school.application.usecases.UserDetailsImpl;
import com.fzhunio.school.config.JwtUtils;
import com.fzhunio.school.frameworks.repositories.UserRepository;
import com.fzhunio.school.role.RoleEntity;
import com.fzhunio.school.role.RoleEnum;
import com.fzhunio.school.domain.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

     @PostMapping("/login")
     public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
         SecurityContextHolder.getContext().setAuthentication(authentication);
         String jwt = jwtUtils.generateToken(loginRequest.getUsername());

         UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
         List<String> roles = userDetails.getAuthorities().stream()
                 .map(item -> item.getAuthority())
                 .collect(Collectors.toList());

         var response = new JwtResponse();
         response.setRoles(roles);
         response.setToken(jwt);
         response.setId(userDetails.getId());
         response.setEmail(userDetails.getEmail());
         response.setUsername(userDetails.getUsername());

         return ResponseEntity.ok(response);
     }

    @GetMapping("login")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok("Todo bien");
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid CreateUserDto request) {
        // Set<RoleEntity> roles = request.getRoles()
        // .stream().map(role -> RoleEntity.builder()
        // .name(RoleEnum.valueOf(role)).build())
        // .collect(Collectors.toSet());

        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .roles(Set.of(RoleEntity.builder().name(RoleEnum.CUSTOMER).build())).build();
        userRepository.save(user);
        return ResponseEntity.ok("user");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam String id) {
        userRepository.deleteById(Long.parseLong(id));
        return new ResponseEntity<>("Destroy Result", HttpStatus.OK);
    }

}
