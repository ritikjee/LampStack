package tech.lampstack.user_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String appSecret = request.getHeader("x-app-secret");
        if (appSecret == null || appSecret.isEmpty()) {
            Map<String, Object> errorResponse = new HashMap<>();

            errorResponse.put("status", 401);
            errorResponse.put("error", "Unauthorized");
            errorResponse.put("message", "Missing or invalid API key");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");


            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            return false;
        }
        return true;
    }
}
