
package com.example.ex2_ElhananBuff.servletPackage;

import com.example.ex2_ElhananBuff.javaPackage.DataStructures;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.json.*;
import java.io.OutputStream;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import java.util.ArrayList;

/**
 * this servlet is not accessed directly.
 *
 * A servlet reading parameters:
 * the parameters are passed by another Servlet (DispatcherServlet_
 * It receives the request parameter "userName" and a request attribute "someparam"
 * from the Dispatcher and returns a response to the client.
 *
 */

@WebServlet(name = "JsonQuestionServlet", value = "/JsonQuestionServlet")
public class JsonQuestionServlet extends HttpServlet {

    /**
     * this function get from the servlet context function all question i want to show to the user  and also get how many
     * responsse i have for any question and return an json with these information
     * @param request servlet
     * @param response servlet
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ServletContext context = getServletContext();
        DataStructures data = (DataStructures) context.getAttribute("DataStructures");
        ArrayList<String> temp = data.getQuestion();
        JsonArrayBuilder arraBuild = Json.createArrayBuilder();
        for (int i = 0; i < temp.size(); i++) {
            JsonObjectBuilder questionBuilder = Json.createObjectBuilder()
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

    /**
     *this servlet  get an post request from the user and with the cookies is can know to witch question i want to answer
     * and with the servlet context function i get from  the database class all response i want to show for an specific
     * question  and who write it and return the request as a json object
     * @param  request  servlet
     * @param response servlet
     * @throws IOException if an I/O error occurs
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        ServletContext context = getServletContext();
        DataStructures data = null;
        if (context.getAttribute("DataStructures") instanceof DataStructures) {
             data = (DataStructures) context.getAttribute("DataStructures");
        }

        assert data != null;
        ArrayList<String> temp = data.getQuestion();
        int number;
        for (Cookie aCookie : cookies)
            if (aCookie.getName().equals("number")) {
                number = Integer.parseInt(aCookie.getValue());
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
