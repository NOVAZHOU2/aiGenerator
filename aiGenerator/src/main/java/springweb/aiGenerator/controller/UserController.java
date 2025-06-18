package springweb.aiGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import springweb.aiGenerator.dto.request.ChangePasswordDTO;
import springweb.aiGenerator.dto.request.LoginDTO;
import springweb.aiGenerator.dto.request.RegisterDTO;
import springweb.aiGenerator.entity.User;
import springweb.aiGenerator.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    // 注册接口
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterDTO registerDTO) {
        User newUser = userService.register(registerDTO);
        return ResponseEntity.ok(newUser);
    }

    // 登录接口
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        User user = userService.login(loginDTO);
        session.setAttribute("userId", user.getId());
        return ResponseEntity.ok(user);
    }

    // 修改密码接口
    @PostMapping("/change-password") // 改为 POST 更符合 RESTful 风格
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        userService.changePassword(changePasswordDTO);
        return ResponseEntity.ok().build();
    }

    // 退出登录接口（保持不变）
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }
}