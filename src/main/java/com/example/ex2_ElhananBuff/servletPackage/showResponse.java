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
        int n = Integer.parseInt(request.getParameter("numberOfResponse"));
        ArrayList<String> resp = data.getResponse(n);
        ArrayList<String> author = data.getAuthor(n);
        JsonArrayBuilder arrayBuild = Json.createArrayBuilder();
        for (int i = 0; i < resp.size(); i++) {
            JsonObjectBuilder questionBuilder = Json.createObjectBuilder()
                    .add("Author", author.get(i))
                    .add("Question", resp.get(i));

            JsonObject questionJson = questionBuilder.build();
            arrayBuild.add(questionJson);
        }
        try (OutputStream out = response.getOutputStream()) {
            JsonWriter jsonw = Json.createWriter(out);
            jsonw.write(arrayBuild.build());
            jsonw.close();
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
