package com.banking;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.CloseAccountDAO;

@WebServlet("/CloseAccountServlet")
public class CloseAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session and logged-in account number
        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("account_number");

        // Perform account closure
        CloseAccountDAO closeAccountDAO = new CloseAccountDAO();
        boolean success = closeAccountDAO.closeAccount(accountNumber);

        if (success) {
            response.sendRedirect("CloseAccountSuccess.jsp");
        } else {
            response.sendRedirect("CloseAccountFailure.jsp");
        }
    }
}
