package springweb.aiGenerator.service;

import springweb.aiGenerator.dto.request.ChangePasswordDTO;
import springweb.aiGenerator.dto.request.LoginDTO;
import springweb.aiGenerator.dto.request.RegisterDTO;
import springweb.aiGenerator.dto.request.ChangePasswordDTO;
import springweb.aiGenerator.entity.User;

public interface UserService {
    User register(RegisterDTO registerDTO);
    User login(LoginDTO loginDTO);
    void changePassword(ChangePasswordDTO changePasswordDTO);
}