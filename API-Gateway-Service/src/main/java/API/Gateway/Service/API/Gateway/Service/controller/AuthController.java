package API.Gateway.Service.API.Gateway.Service.controller;

import API.Gateway.Service.API.Gateway.Service.dto.AuthDTO;

import API.Gateway.Service.API.Gateway.Service.service.MyUserDetailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private MyUserDetailService myUserDetailService;

    public AuthController(AuthenticationManager authenticationManager, MyUserDetailService myUserDetailService) {
        this.authenticationManager = authenticationManager;
        this.myUserDetailService = myUserDetailService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO req, HttpSession session) {

        myUserDetailService.loadUserByUsername(req.getUserName());

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(req.getUserName(), req.getPassword());

        Authentication auth = authenticationManager.authenticate(token);

        session.setAttribute("userName", req.getUserName());
        session.setAttribute("role", req.getUserType());

        System.out.println("DEBUG: " + auth.getPrincipal());
        System.out.println("userName: " + req.getUserName() + ", role: " + auth.getAuthorities());

        return ResponseEntity.ok(Map.of(
                "message", "logged in"
        ));
    }

}

