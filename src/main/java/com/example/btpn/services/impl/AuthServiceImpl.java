package com.example.btpn.services.impl;

import com.example.btpn.dto.request.AuthRequest;
import com.example.btpn.dto.request.LoginRequest;
import com.example.btpn.dto.response.LoginResponse;
import com.example.btpn.dto.response.RegisterResponse;
import com.example.btpn.entity.UserCredential;
import com.example.btpn.security.JwtUtil;
import com.example.btpn.services.AuthService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Override
    public RegisterResponse registerMerchant(AuthRequest request) {
        return null;
    }

    @Override
    public RegisterResponse confirmEmail(String token) {
        return null;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        return null;
    }
    /*private final UserCredentialRepository userCredentialRepository;
    private final BCryptUtil bCryptUtil;
    private final JwtUtil jwtUtil;
    private final ValidationUtil validationUtil;
    private final AuthenticationManager authenticationManager;
    private final MerchantService merchantService;
    private final OtpService otpService;
    private final EmailService emailService;
    private final AdminService adminService;

    private String adminEmail = "admin@gmail.com";
    private String password = "password";


    @Value("${spring.mail.username}")
    private String formMail;
    @PostConstruct
    private void init() {
        registerSuperAdmin(AuthRequest.builder()
                .email(adminEmail)
                .password(password)
                .name("admin")
                .build());
    }

    private void registerSuperAdmin(AuthRequest request) {
        log.info("Start registerSuperAdmin");
        validationUtil.validate(request);
        Optional<UserCredential> username = userCredentialRepository.findByEmail(request.getEmail());

        if (username.isPresent()) return;

        UserCredential userCredential = UserCredential.builder()
                .email(request.getEmail().toLowerCase())
                .password(bCryptUtil.hashPassword(request.getPassword()))
                .role(ERole.ROLE_ADMIN)
                .iSenabled(true)
                .build();
        userCredentialRepository.saveAndFlush(userCredential);
        Admin admin = Admin.builder()
                .userCredential(userCredential)
                .name(request.getName())
                .build();
        adminService.createNew(admin);

        log.info("End registerSuperAdmin");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse registerMerchant(AuthRequest request) {
        try {
            validationUtil.validate(request);
            UserCredential userCredential = UserCredential.builder()
                    .email(request.getEmail().toLowerCase())
                    .password(bCryptUtil.hashPassword(request.getPassword()))
                    .role(ERole.ROLE_MERCHANT)
                    .iSenabled(false)
                    .build();
            userCredentialRepository.saveAndFlush(userCredential);

//            log.info("id user: "+userCredential.getId());
            Integer time = 24*60;
            String otp = otpService.createOtp(userCredential,time);


            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(formMail);
            mailMessage.setTo(userCredential.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setText("To confirm your account, please click here : "
                    + "http://localhost:8080/api/auth/confirm-account?token=" + otp);
            emailService.sendEmail(mailMessage);

            Merchant merchant = Merchant.builder()
                    .name(request.getName())
                    .userCredential(userCredential)
                    .address(request.getAddres())
                    .phoneNumber(request.getPhoneNumber())
                    .balance(0L)
                    .pan(request.getPan())
                    .build();
            merchantService.save(merchant);
            return mapToResponse(userCredential);
        } catch (DataIntegrityViolationException e) {
            log.error("Error registerCustomer: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already exist");
        }
    }

    @Override
    public RegisterResponse registerMerchant(AuthRequest request) {
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse confirmEmail(String token) {
        OneTimePassword confirmationToken = otpService.findConfirmationToken(token);
        otpService.delete(confirmationToken.getId());
        UserCredential userCredential = userCredentialRepository.findByEmail(confirmationToken.getUser().getEmail()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found")
        );
        userCredential.setISenabled(true);
        userCredentialRepository.saveAndFlush(userCredential);
        return mapToResponse(userCredential);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        return null;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        log.info("Start login");
        validationUtil.validate(request);
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail().toLowerCase(),
                request.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        UserCredential user = (UserCredential) authenticate.getPrincipal();
        String token = jwtUtil.generateToken(user);
        log.info("End login");

        return LoginResponse.builder()
                .token(token)
                .role(user.getRole().name())
                .build();
    }

    private RegisterResponse mapToResponse(UserCredential userCredential) {
        return RegisterResponse.builder()
                .email(userCredential.getEmail())
                .role(userCredential.getRole().name())
                .build();
    }*/
}
