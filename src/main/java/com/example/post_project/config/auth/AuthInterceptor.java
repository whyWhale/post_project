package com.example.post_project.config.auth;

import com.example.post_project.config.auth.dto.SessionUser;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod)handler;
        HttpSession httpSession=request.getSession();
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user!=null)
            return true;

        return false;
    }
}
