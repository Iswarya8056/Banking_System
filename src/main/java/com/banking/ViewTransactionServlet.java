package com.banking;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.ViewTransactionDAO;
import bankmodel.Transaction;

@WebServlet("/ViewTransactionServlet")
public class ViewTransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session and logged-in account number
        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("account_number");

        // Check if accountNumber is not null
        if (accountNumber == null || accountNumber.isEmpty()) {
            response.sendRedirect("CustomerLogin.html");
            return;
        }

        // Retrieve transactions
        ViewTransactionDAO transactionDAO = new ViewTransactionDAO();
        List<Transaction> transactions = transactionDAO.getTransactions(accountNumber);

        // Set transactions as request attribute and forward to JSP
        request.setAttribute("transactions", transactions);
        request.getRequestDispatcher("ViewTransaction.jsp").forward(request, response);
    }
}
