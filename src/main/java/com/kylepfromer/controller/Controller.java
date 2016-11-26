package com.kylepfromer.controller;

import com.kylepfromer.database.AESEncryption;
import com.kylepfromer.database.Account;
import com.kylepfromer.database.MySQLDatabaseHandler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
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

    private Map<String, String> pageLayout;
    private Connection connection;
    private Account account;

    public void init(ServletConfig config) throws ServletException

    {
        //Database Setup
        connection = MySQLDatabaseHandler.getConnection();
        account = new Account(connection);

        //Page Setup
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

    public void destroy() {
        try {
            account.destroy();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //Signing Up
        if (request.getParameter("action").equals("signup")) {
            String username = request.getParameter("user_name");
            String password = request.getParameter("user_password");
            String email = request.getParameter("user_mail");

            try {
                if (account.createAccount(username, AESEncryption.encrypt(password), email)) {
                    response.sendRedirect("/?action=login");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        } else if (request.getParameter("action").equals("login")) {

            String username = request.getParameter("user_name");
            String password = request.getParameter("user_password");

            PrintWriter out = response.getWriter();
            if (account.login(username, AESEncryption.encrypt(password))) {
                //todo: add some session code, like get person id
                out.print(account.getID(username, AESEncryption.encrypt(password)));
//                response.sendRedirect("/");
            } else {
                response.sendRedirect("/?action=login");
            }

//            String plainText = "Hello World";
//            try {
//                SecretKey secKey = getSecretEncryptionKey();
//                byte[] cipherText = encryptText(plainText, secKey);
//                String decryptedText = decryptText(cipherText, secKey);
//
//                out.println("Original Text:" + plainText);
//                out.println("AES Key (Hex Form):"+ AESEncryption.bytesToHex(secKey.getEncoded()));
//                out.println("Encrypted Text (Hex Form):"+AESEncryption.bytesToHex(cipherText));
//                out.println("Descrypted Text:"+decryptedText);
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }

        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        if (request.getParameter("action") != null) {
            //if wanting to create an account
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
