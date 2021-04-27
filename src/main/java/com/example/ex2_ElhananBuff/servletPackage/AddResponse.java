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
        String questionNum =  request.getParameter("questionNumber");
        if(questionNum == null)
            response.sendRedirect("MainPage");
        Cookie cookie = new Cookie("number",questionNum);
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
        request.getRequestDispatcher("html/addResponse.html").include(request, response);
}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
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
        if (data == null) throw new AssertionError();
        String[] myData = new String [] {request.getParameter("text"),request.getParameter("myName")};

        if ((myData[0] != null && !myData[0].equals("")) || (myData[1] != null && !myData[1].equals(""))) {
            data.setResponse(number, myData[0], myData[1]);
        }
        response.sendRedirect("/MainPage");

    }
}
