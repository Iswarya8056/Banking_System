package com.banking;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.ViewBalanceDAO;

@WebServlet("/ViewBalanceServlet")
public class ViewBalanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session and logged-in account number
        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("account_number");

        // Retrieve current balance
        ViewBalanceDAO balanceDAO = new ViewBalanceDAO();
        double balance = balanceDAO.getCurrentBalance(accountNumber);

        // Set balance as request attribute and forward to JSP
        request.setAttribute("balance", balance);
        request.getRequestDispatcher("ViewBalance.jsp").forward(request, response);
    }
}
