package com.example.ex2_ElhananBuff.servletPackage;

import com.example.ex2_ElhananBuff.javaPackage.DataStructures;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddResponse", value = "/AddResponse")
public class AddResponse extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Cookie cookie = new Cookie("number", request.getParameter("questionNumber"));
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
        request.getRequestDispatcher("showQuestion.html").include(request, response);
}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        int number = 0;
        for (Cookie aCookie : cookies)
            if (aCookie.getName().equals("number"))
            {
                number = Integer.parseInt(aCookie.getValue());
                aCookie.setMaxAge(0);
            }
        ServletContext context = getServletContext();
        DataStructures data = null;
        if (context.getAttribute("DataStructures") instanceof DataStructures) {
            data = (DataStructures) context.getAttribute("DataStructures");
        }
        data.setResponse(number,request.getParameter("text"),request.getParameter("myName"));
        response.sendRedirect("/MainPage");
    }
}
