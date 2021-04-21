
package com.example.ex2_ElhananBuff;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;


import javax.servlet.annotation.WebServlet;
import javax.json.*;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import java.util.ArrayList;

@WebServlet(name = "JsonQuestionServlet", value = "/JsonQuestionServlet")
public class JsonQuestionServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ServletContext context = getServletContext();
        DataStructures data = (DataStructures) context.getAttribute("DataStructures");

        ArrayList<String> temp = data.getQuestion();
        JsonArrayBuilder arraBuild = Json.createArrayBuilder();
        for (int i = 0; i < temp.size(); i++) {
            JsonObjectBuilder questionBuilder = Json.createObjectBuilder()
                    .add("number", i)
                    .add("Question", temp.get(i))
                    .add("numberOfAnswer", data.getLenght(i));
            JsonObject questionJson = questionBuilder.build();
            arraBuild.add(questionJson);
        }
        try (OutputStream out = response.getOutputStream()) {
            JsonWriter jsonw = Json.createWriter(out);
            jsonw.write(arraBuild.build());
            jsonw.close();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8"); Cookie[] cookies = request.getCookies();
        ServletContext context = getServletContext();
        DataStructures data = null;
        if (context.getAttribute("DataStructures") instanceof DataStructures) {
             data = (DataStructures) context.getAttribute("DataStructures");
        }
        ArrayList<String> temp = data.getQuestion();
        int number = 0;
        for (Cookie aCookie : cookies)
            if (aCookie.getName().equals("number")) {
                number = Integer.parseInt(aCookie.getValue());
                System.out.println(number);
                JsonObjectBuilder questionBuilder = Json.createObjectBuilder()
                        .add("number", number)
                        .add("Question", temp.get(number));
                JsonObject questionJson = questionBuilder.build();
                try (OutputStream out = response.getOutputStream()) {
                    JsonWriter jsonw = Json.createWriter(out);
                    jsonw.write(questionJson);
                    jsonw.close();
                }
                Cookie cookie = new Cookie("number", Integer.toString(number));
                cookie.setMaxAge(3600);
                response.addCookie(cookie);

            }
    }
}
