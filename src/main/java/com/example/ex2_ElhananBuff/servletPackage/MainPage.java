package com.example.ex2_ElhananBuff.servletPackage;

import com.example.ex2_ElhananBuff.javaPackage.DataStructures;
import com.example.ex2_ElhananBuff.javaPackage.File;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import javax.servlet.ServletContext;

@WebServlet(name = "MainPage", value = "/MainPage",initParams = {@WebInitParam(name="questionPath", value= "questions.txt")})
public class MainPage extends HttpServlet {


    @Override   public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String pathName = this.getServletContext().getInitParameter("pathName");
        String myPath = getServletContext().getRealPath(pathName);
        File questionFile =  new File(myPath);
        ServletContext context = getServletContext();
         try {
             context.setAttribute("DataStructures", new DataStructures(questionFile.getQuestion()));

         }
         catch (Exception e){
            System.out.println(e.getMessage());
       }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher(getServletContext().getRealPath("addQuestion.html")).include(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
