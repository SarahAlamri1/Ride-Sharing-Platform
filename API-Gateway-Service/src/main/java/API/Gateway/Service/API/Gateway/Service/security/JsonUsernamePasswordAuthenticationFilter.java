//package API.Gateway.Service.API.Gateway.Service.security;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.IOException;
//import java.util.Map;
//
//public class JsonUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//            throws AuthenticationException {
//
//        System.out.println("DEBUG: request: " + request.getRequestURI() + " response: " + response.getStatus());
//
//        if (!MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(request.getContentType())) {
//            System.out.println("DEBUG: Content type is not JSON, fallback to default");
//            return super.attemptAuthentication(request, response);
//        }
//
//        try {
//            System.out.println("DEBUG: Parsing JSON login request");
//
//            ObjectMapper mapper = new ObjectMapper();
//            Map<String, String> authRequest = mapper.readValue(request.getInputStream(), Map.class);
//            String username = authRequest.get("username");
//            String password = authRequest.get("password");
//
//            System.out.println("DEBUG: username: " + username + ", password: " + password);
//
//            if (username == null || password == null) {
//                throw new AuthenticationServiceException("username/password missing in JSON");
//            }
//
//            UsernamePasswordAuthenticationToken authToken =
//                    new UsernamePasswordAuthenticationToken(username, password);
//
//            setDetails(request, authToken);
//            System.out.println("DEBUG:  authenticated authToken : " + authToken);
//            SecurityContextHolder.getContext().setAuthentication(authToken);
//            return this.getAuthenticationManager().authenticate(authToken);
//
//        } catch (IOException e) {
//            throw new AuthenticationServiceException("Failed to parse JSON request", e);
//        }
//    }
//}
