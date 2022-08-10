package capstone.sangcom.controller.api.login;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.controller.api.response.login.LoginResponse;
import capstone.sangcom.dto.loginSection.login.LoginDTO;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.service.login.LoginService;
import capstone.sangcom.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService userService;
    private final UserService user2Service;

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO) {
        LoginResponse response = userService.login(loginDTO);
        if (response == null)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        else
            return ResponseEntity.
                    ok(response);
    }
    /**
     * 회원 탈퇴
     */
    @DeleteMapping("/quit")
    public ResponseEntity<SimpleResponse> userOut(HttpServletRequest request){
        JwtUser user = (JwtUser) request.getAttribute("user");

        if (user2Service.leave(user.getId()))
            return ResponseEntity.
                    ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
    }
}
