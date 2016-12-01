package com.kylepfromer.controller;

import com.kylepfromer.database.AESEncryption;
import com.kylepfromer.database.Account;
import com.kylepfromer.database.MySQLDatabaseHandler;
import com.kylepfromer.todo.Task;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by kpfromer on 11/16/16.
 */
@javax.servlet.annotation.WebServlet(name = "Controller")
public class Controller extends javax.servlet.http.HttpServlet {
    //todo: Comment code
    private Map<String, String> pageLayout;
    private Connection connection;
    private Account account;

    public void init(ServletConfig config) throws ServletException

    {
        //Database Setup
        connection = MySQLDatabaseHandler.getConnection();
        if (connection == null) {
            throw new RuntimeException("Null Connection");
        }
        account = new Account(connection);

        //Page Setup
        pageLayout = new HashMap<String, String>();
        String rootDir = "/WEB-INF/jsps/";

        //Root Files
        pageLayout.put("index", "index.jsp");
        pageLayout.put("about", "about.jsp");
        pageLayout.put("login", "login.jsp");
        pageLayout.put("signup", "signup.jsp");
        pageLayout.put("createTask", "createTask.jsp");
        pageLayout.put("listTasks", "listTasks.jsp");


        Set<String> pageKeys = pageLayout.keySet();
        //todo: change this system of adding files
        if (!pageKeys.contains("index") || !pageKeys.contains("login") || !pageKeys.contains("signup") || !pageKeys.contains("createTask") || !pageKeys.contains("listTasks")) {
            throw new RuntimeException("Doesn't have the required files!");
        }

        //Add rootDir to every item
        for ( String key : pageLayout.keySet() ) {
            pageLayout.replace(key, rootDir+pageLayout.get(key));
        }
    }

    public void destroy() {
        try {
            account.destroy();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //Signing Up
        String location = request.getParameter("action");
        if (location.equals("signup")) {
            String username = request.getParameter("user_name");
            String password = request.getParameter("user_password");
            String email = request.getParameter("user_mail");

            try {
                if (account.createAccount(username, AESEncryption.encrypt(password), email)) {
                    request.getRequestDispatcher(pageLayout.get("login")).forward(request, response);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        } else if (location.equals("login")) {

            String username = request.getParameter("user_name");
            String password = request.getParameter("user_password");

            if (account.login(username, AESEncryption.encrypt(password))) {
                //todo: add some session code, like get person id

                HttpSession session = request.getSession();
                session.setAttribute("ID", account.getID(username, AESEncryption.encrypt(password)));
                response.sendRedirect("/?action=createTask");
            } else {
                response.sendRedirect("/?action=error");
            }
        } else if (location.equals("createTask")) {
            HttpSession session = request.getSession();
            if (session.getAttribute("ID").toString() == null) {
                request.getRequestDispatcher(pageLayout.get("login")).forward(request, response);
            }

            String taskText = request.getParameter("task_text");
            long userId = Integer.parseInt(session.getAttribute("ID").toString());

            //todo: get all items and position and dynamically set it!
            int taskPosition = 0;

            //todo: when getting date, take in account if the user doesn't live in the right timezone
            Task newTask = new Task(taskText, userId, taskPosition);
            PrintWriter out = response.getWriter();
            out.println("Text: " + newTask.getmText() + " Due date: " + newTask.getmDueDate());
            out.print(newTask.getmSchoolClass().getContactInfo());
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //todo: change this!
        String indexURL = pageLayout.get("index");
        if (request.getParameter("action") != null) {
            String location = request.getParameter("action");
            String locationURL = pageLayout.get(location);
            try {
                if (location.equals("createTask")) {
                    HttpSession session = request.getSession();
                    if (session.getAttribute("ID").toString() == null) {
                        request.getRequestDispatcher(pageLayout.get("login")).forward(request, response);
                    } else {
                        request.getRequestDispatcher(locationURL).forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher(locationURL).forward(request, response);
                }
            } catch (NullPointerException e){
                request.getRequestDispatcher(indexURL).forward(request, response);
            }
        } else {
//            request.setAttribute("todo", "10");
            request.getRequestDispatcher(indexURL).forward(request, response);
        }

    }
}
