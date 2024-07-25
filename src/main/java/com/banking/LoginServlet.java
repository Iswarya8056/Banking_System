package com.banking;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.LoginDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDAO userDAO;

    public LoginServlet() {
        super();
        userDAO = new LoginDAO(); // Initialize DAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handling GET requests (if any)
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Perform validation, authentication, and role retrieval logic
        String role = userDAO.getUserRole(username, password);

        if (role != null && role.equals("admin")) {
            // Redirect to admin success page
            response.sendRedirect("Adminsuccess.html");
        } else {
            // Redirect to admin failure page
            response.sendRedirect("Adminfailure.html");
        }
    }
}
