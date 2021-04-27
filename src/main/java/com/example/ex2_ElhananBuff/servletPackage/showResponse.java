package com.example.ex2_ElhananBuff.servletPackage;

import com.example.ex2_ElhananBuff.javaPackage.DataStructures;

import javax.json.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

@WebServlet(name = "showResponse", value = "/showResponse")
public class showResponse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ServletContext context = getServletContext();
        DataStructures data = (DataStructures) context.getAttribute("DataStructures");
        String numOfResp = request.getParameter("numberOfResponse");
        if(numOfResp == null/*||String.isDigit(numOfResp)*/)
            response.sendRedirect("MainPage");
        else {
            ArrayList<String> resp = data.getResponse(Integer.parseInt(numOfResp));
            ArrayList<String> author = data.getAuthor(Integer.parseInt(numOfResp));
            JsonArrayBuilder arrayBuild = Json.createArrayBuilder();
            for (int i = 0; i < resp.size(); i++) {
                JsonObjectBuilder questionBuilder = Json.createObjectBuilder()
                        .add("Author", author.get(i))
                        .add("Response", resp.get(i));

                JsonObject questionJson = questionBuilder.build();
                arrayBuild.add(questionJson);
            }
            try (OutputStream out = response.getOutputStream()) {
                JsonWriter jsonElement = Json.createWriter(out);
                jsonElement.write(arrayBuild.build());
                jsonElement.close();
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
