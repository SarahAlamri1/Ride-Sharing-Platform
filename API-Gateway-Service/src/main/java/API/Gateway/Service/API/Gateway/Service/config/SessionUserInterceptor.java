package API.Gateway.Service.API.Gateway.Service.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionUserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String path = request.getRequestURI();
        HttpSession session = request.getSession(true);
//        session.setMaxInactiveInterval(1800);// 30m

        if (path.startsWith("/auth/login")) {
            return true;
        }

        //HttpSession session = request.getSession(false);
//        HttpSession session = request.getSession(true);
//        session.setMaxInactiveInterval(60);

        if (session != null) {
            String username = (String) session.getAttribute("username");
            String role = (String) session.getAttribute("role");
            if (username != null) {
                request.setAttribute("X-User-Username", username);
//                request.setAttribute("X-User-Role", role);
            }
        }

        return true;
    }
}
