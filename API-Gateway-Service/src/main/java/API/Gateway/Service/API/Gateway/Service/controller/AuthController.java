package API.Gateway.Service.API.Gateway.Service.controller;

import API.Gateway.Service.API.Gateway.Service.dto.AuthDTO;

import API.Gateway.Service.API.Gateway.Service.service.MyUserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;

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


//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody AuthDTO req, WebSession session) {
//
//        myUserDetailService.loadUserByUsername(req.getUserName());
//
//        UsernamePasswordAuthenticationToken token =
//                new UsernamePasswordAuthenticationToken(req.getUserName(), req.getPassword());
//
//        Authentication auth = authenticationManager.authenticate(token);
//
//        session.getAttributes().put("username", req.getUserName());
//        session.getAttributes().put("role", req.getUserType());
//        session.setMaxIdleTime(java.time.Duration.ofMinutes(1));
//
//        System.out.println("DEBUG: " + auth.getPrincipal());
//        System.out.println("userName: " + req.getUserName() + ", role: " + auth.getAuthorities());
//
//        return ResponseEntity.ok(Map.of(
//                "message", "logged in"
//        ));
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO req, HttpServletRequest request) {

        myUserDetailService.loadUserByUsername(req.getUserName());

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(req.getUserName(), req.getPassword());

        Authentication auth = authenticationManager.authenticate(token);

        // Invalidate old session
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }else{
            // Create new session
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("username", req.getUserName());
            newSession.setAttribute("role", req.getUserType());
            newSession.setMaxInactiveInterval(60 * 30); // 30 minutes
            System.out.println("Session ID: " + newSession.getId());
        }




        System.out.println("userName: " + req.getUserName() + ", role: " + auth.getAuthorities());

        return ResponseEntity.ok(Map.of(
                "message", "logged in",
                "sessionId", "newSession.getId()"
        ));
    }

}

//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
//
//        // 1. Validate credentials (your existing logic)
//        User user = authenticateUser(loginRequest);
//
//        if (user == null) {
//            return ResponseEntity.status(401).body("Invalid credentials");
//        }
//
//        // 2. Invalidate old session (IMPORTANT - prevents session fixation)
//        HttpSession oldSession = request.getSession(false);
//        if (oldSession != null) {
//            oldSession.invalidate();
//        }
//
//        // 3. Create NEW session for this user
//        HttpSession newSession = request.getSession(true);
//
//        // 4. Set user attributes in the NEW session
//        newSession.setAttribute("username", user.getUsername());
//        newSession.setAttribute("role", user.getRole());
//
//        return ResponseEntity.ok("Login successful");
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//        return ResponseEntity.ok("Logged out");
//    }
//}