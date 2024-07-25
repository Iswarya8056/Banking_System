package com.banking;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.DepositDAO;

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the deposit form
        request.getRequestDispatcher("Deposit.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session and logged-in account number
        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("account_number");
        double depositAmount = Double.parseDouble(request.getParameter("amount"));

        // Create DAO instance and perform deposit
        DepositDAO depositDAO = new DepositDAO();
        boolean success = depositDAO.depositAmount(accountNumber, depositAmount);

        if (success) {
            response.sendRedirect("DepositSuccess.html");
        } else {
            response.sendRedirect("DepositFailure.html");
        }
    }
}
