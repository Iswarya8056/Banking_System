package com.banking;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DashboardRedirectServlet")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DashboardServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("CreateAccount".equals(action)) {
            response.sendRedirect("CreateAccount.html");
        } else if ("ViewAccount".equals(action)) {
            response.sendRedirect("ViewAccount.html");
        } else if ("TransactionHistory".equals(action)) {
            response.sendRedirect("EditAccount.jsp");
        } else if ("EditAccount".equals(action)) {
            response.sendRedirect("DeleteAccount.html");
        } else {
            response.sendRedirect("failure.html"); // Redirect to an error page if action is not recognized
        }
    }
}
