package com.example.ex2_ElhananBuff;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import javax.servlet.ServletContext;

@WebServlet(name = "MainPage", value = "/MainPage",initParams={@WebInitParam(name="questionPath", value="../questions.txt")})
public class MainPage extends HttpServlet {



    @Override   public void init(ServletConfig config) throws ServletException {
        super.init(config);
        File questionFile =  new File(getServletContext().getInitParameter("questionPath"));
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
        request.getRequestDispatcher("addQuestion.html").include(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
