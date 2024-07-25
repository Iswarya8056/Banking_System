package com.banking;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.WithdrawDAO;

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the withdraw form
        request.getRequestDispatcher("Withdraw.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session and logged-in account number
        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("account_number");
        double withdrawAmount = Double.parseDouble(request.getParameter("amount"));

        // Create DAO instance and perform withdrawal
        WithdrawDAO withdrawDAO = new WithdrawDAO();
        boolean success = withdrawDAO.withdrawAmount(accountNumber, withdrawAmount);

        if (success) {
            response.sendRedirect("WithdrawSuccess.html");
        } else {
            response.sendRedirect("WithdrawFailure.html");
        }
    }
}
