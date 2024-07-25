package com.banking;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.LoginDAO1;

@WebServlet("/LoginServlet1")
public class LoginServlet1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("account_number");
        String password = request.getParameter("password");

        LoginDAO1 loginDAO = new LoginDAO1();
        boolean isValidUser = loginDAO.validate(accountNumber, password);

        if (isValidUser) {
            HttpSession session = request.getSession();
            session.setAttribute("account_number", accountNumber);
            response.sendRedirect("CustomerSuccess.html"); // Redirect to the dashboard after successful login
        } else {
            request.setAttribute("error", "Invalid account number or password.");
            request.getRequestDispatcher("Login.html").forward(request, response);
        }
    }
}
