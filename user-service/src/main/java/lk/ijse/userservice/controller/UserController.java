package lk.ijse.userservice.controller;

import lk.ijse.userservice.dto.AuthDTO;
import lk.ijse.userservice.dto.ResponseDTO;
import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.service.UserService;
import lk.ijse.userservice.util.JwtUtil;
import lk.ijse.userservice.util.VarList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> Register(@RequestBody UserDTO userDTO) {
        try{
            int res = userService.addUser(userDTO);
            switch (res){
                case VarList.Created -> {


                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", userDTO));
                }
                case VarList.Not_Acceptable -> {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseDTO(VarList.Not_Acceptable, "User name Already Used", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> Login( @RequestBody UserDTO userDTO) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(new ResponseDTO(VarList.Unauthorized, "Invalid Credentials", e.getMessage()));
//        }

        UserDTO loadedUser = userService.loadUserDetailsByUsername(userDTO.getUsername());
        String accessToken = jwtUtil.generateToken(loadedUser);
        String refreshToken = jwtUtil.generateRefreshToken(loadedUser);

        AuthDTO authDTO = new AuthDTO();
        authDTO.setUsername(loadedUser.getUsername());
        authDTO.setToken(accessToken);
        authDTO.setRefreshToken(refreshToken);
        if (loadedUser == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseDTO(VarList.Conflict, "Authorization Failure! Please Try Again", null));
        }

        String token = jwtUtil.generateToken(loadedUser);
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseDTO(VarList.Conflict, "Authorization Failure! Please Try Again", null));
        }


        authDTO.setUsername(loadedUser.getUsername());
        authDTO.setToken(token);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Success", authDTO));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ResponseDTO> Refresh(@RequestBody Map<String, String> payload) {
        String refreshToken = payload.get("refreshToken");
        if (refreshToken != null && jwtUtil.validateToken(refreshToken)) {
            String username = jwtUtil.extractUsername(refreshToken);
            UserDTO user = userService.loadUserDetailsByUsername(username);

            String newAccessToken = jwtUtil.generateToken(user);

            AuthDTO authDTO = new AuthDTO();
            authDTO.setUsername(username);
            authDTO.setToken(newAccessToken);
            authDTO.setRefreshToken(refreshToken);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK, "Token Refreshed", authDTO));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseDTO(VarList.Unauthorized, "Invalid Refresh Token", null));
    }

}
