package API.Gateway.Service.API.Gateway.Service.security;


import API.Gateway.Service.API.Gateway.Service.repositry.UserRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@Component
//public class UserHeaderFilter extends OncePerRequestFilter {
//
//    private final UserRepo userRepo;
//    private final RestTemplate restTemplate;
//
//    public UserHeaderFilter(UserRepo userRepo,  RestTemplate restTemplate) {
//        this.userRepo = userRepo;
//        this.restTemplate = restTemplate;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        System.out.println("DEBUG: UserHeaderFilter");
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("DEBUG: UserHeaderFilter auth: " + auth);
//
//        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())){
//            System.out.println("DEBUG: UserHeaderFilter in if");
//
//            String loggedUsername = auth.getName();
//            String userId = userRepo.findByUsername(loggedUsername)
//                    .map(u -> String.valueOf(u.getId()))
//                    .orElse("0");
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("X-User-Id", userId);
//            System.out.println("DEBUG: UserHeaderFilter"+ "X-User-Id"+ userId);
//
//            HttpEntity<String> entity = new HttpEntity<>(headers);
//
//            if (request.getRequestURI().startsWith("/customer/my-orders")) {
//                System.out.println("DEBUG: UserHeaderFilter check request.getRequestURI().startsWith(\"/customer/my-orders\") :"+request.getRequestURI().startsWith("/customer/my-orders"));
//
//                String microserviceUrl = "";
//                if (request.getRequestURI().startsWith("/customer/")) {
//                    microserviceUrl = "http://localhost:8081" + request.getRequestURI();
//                } else if (request.getRequestURI().startsWith("/driver/")) {
//                    microserviceUrl = "http://localhost:8082" + request.getRequestURI();
//                }
//
//                HttpEntity<byte[]> body = new HttpEntity<>(request.getInputStream().readAllBytes(), headers);
//
//                ResponseEntity<String> microResponse = restTemplate.exchange(
//                        microserviceUrl,
//                        HttpMethod.valueOf(request.getMethod()),
//                        body,
//                        String.class
//                );
//
//
//                response.setContentType("application/json");
//                response.getWriter().write(microResponse.getBody());
//                return;
//            }
//        }else {
//            System.out.println("DEBUG: UserHeaderFilter check else :"+(auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())));
////            DEBUG: UserHeaderFilter check :false
//
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}

