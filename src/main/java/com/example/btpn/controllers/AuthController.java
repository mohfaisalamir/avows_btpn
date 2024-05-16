package com.example.btpn.controllers;

import com.example.btpn.dto.request.AuthRequest;
import com.example.btpn.dto.request.LoginRequest;
import com.example.btpn.dto.response.CommonResponse;
import com.example.btpn.dto.response.LoginResponse;
import com.example.btpn.dto.response.RegisterResponse;
import com.example.btpn.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
//    private final AuthService authService;
private final AuthService authService;
    @PostMapping("/register/merchant")
    public ResponseEntity<?> registerMerchant(@RequestBody AuthRequest request){
        RegisterResponse registerResponse = authService.registerMerchant(request);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("successfully register new merchat")
                .statusCode(HttpStatus.CREATED.value())
                .data(registerResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @GetMapping("/confirm-account")
    public ResponseEntity<?> confirmRegister(@RequestParam String token){
        RegisterResponse registerResponse = authService.confirmEmail(token);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("Congratulations! Your email address has been successfully verified. Your account is now active, and you can start enjoying our services")
                .statusCode(HttpStatus.OK.value())
                .data(registerResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        LoginResponse login = authService.login(request);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .message("successfully login")
                .statusCode(HttpStatus.OK.value())
                .data(login)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
