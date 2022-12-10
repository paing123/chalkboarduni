package edu.chalkboarduni.uniregistrationsystem.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.service.UserService;

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
		
	@Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        String loginEmail = auth.getName(); //get logged in user  
        HttpSession session=httpServletRequest.getSession(false);
        session.setAttribute("currentUser", loginEmail);
        
        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin/index");
        } 
        else if (roles.contains("ROLE_STUDENT")){
            httpServletResponse.sendRedirect("/student/index");
        }
        else if (roles.contains("ROLE_FACULTY")){
            httpServletResponse.sendRedirect("/faculty/index");
        }
        else if (roles.contains("ROLE_RESEARCHERSTAFF")){
            httpServletResponse.sendRedirect("/researcherstaff/index");
        }
    }
}
