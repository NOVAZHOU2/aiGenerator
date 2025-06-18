package springweb.aiGenerator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import springweb.aiGenerator.dto.request.ChangePasswordDTO;
import springweb.aiGenerator.dto.request.LoginDTO;
import springweb.aiGenerator.dto.request.RegisterDTO;
import springweb.aiGenerator.entity.User;
import springweb.aiGenerator.exception.*;
import springweb.aiGenerator.repository.UserRepository;
import springweb.aiGenerator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User register(RegisterDTO registerDTO) {
        if (userRepository.existsByName(registerDTO.getName())) {
            throw new UserAlreadyExistsException("用户名已存在");
        }

        User user = new User();
        user.setName(registerDTO.getName());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User login(LoginDTO loginDTO) {
        User user = userRepository.findByName(loginDTO.getName())
                .orElseThrow(() -> new UserNotFoundException("用户不存在"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("密码错误");
        }

        return user;
    }

    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        // 根据用户名查找用户
        User user = userRepository.findByName(changePasswordDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException("用户不存在"));

        // 验证旧密码
        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("原密码错误");
        }

        // 设置新密码
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}