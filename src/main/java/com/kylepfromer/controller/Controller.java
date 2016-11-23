package com.kylepfromer.controller;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by kpfromer on 11/16/16.
 */
@javax.servlet.annotation.WebServlet(name = "Controller")
public class Controller extends javax.servlet.http.HttpServlet {

    private Map<String, String> pageLayout;
    public Controller() {
        pageLayout = new HashMap<String, String>();
        String rootDir = "/WEB-INF/jsps/";

        //Root Files
        pageLayout.put("index", "index.jsp");
        pageLayout.put("about", "about.jsp");
        pageLayout.put("login", "login.jsp");
        pageLayout.put("signup", "signup.jsp");


        //
        Set<String> pageKeys = pageLayout.keySet();

        if (!pageKeys.contains("index") || !pageKeys.contains("login") || !pageKeys.contains("signup")){
            throw new RuntimeException("Doesn't have the required files!");
        }

        //Add rootDir to every item
        for ( String key : pageLayout.keySet() ) {
            pageLayout.replace(key, rootDir+pageLayout.get(key));
        }
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println(request.getParameter("user_name"));
//        HttpSession session = request.getSession();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {


        if (request.getParameter("action") != null) {
            try {
                request.getRequestDispatcher(pageLayout.get(request.getParameter("action"))).forward(request, response);
            } catch (NullPointerException e){
                request.getRequestDispatcher(pageLayout.get("index")).forward(request, response);
            }
        } else {
//            request.setAttribute("todo", "10");
            request.getRequestDispatcher(pageLayout.get("index")).forward(request, response);
        }

    }
}
