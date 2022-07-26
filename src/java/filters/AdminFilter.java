/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author barab
 */
public class AdminFilter implements Filter {

   
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    
        // before we can use HttpServletRequest or HttpServletResponse methods
        // we must cast the ServletRequest and ServletResponse objects as the correct type
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        
        User user  = (User)session.getAttribute("user");
        
        if(!user.getRole().getRoleId().equals(1)){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("notes");
            return;
        }
        
        // any code before chain.doFilter
        // will be executed before the servlet
        chain.doFilter(request, response);
        // any code after chain.doFilter
        //session.setAttribute("isAdmin",true);
    
    
    }

     @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    
    @Override
    public void destroy() {
    }
}