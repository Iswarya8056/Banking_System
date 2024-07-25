package com.banking;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserDashboardServlet")
public class UserDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;  // Add this line to resolve the warning

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "resetPassword":
                // Redirect to password reset page or handle password reset logic
                response.sendRedirect("resetpassword.jsp");
                break;
            case "viewAccountDetails":
                // Redirect to account details page or handle account details logic
                response.sendRedirect("accountdetails.html");
                break;
            case "deposit":
                // Redirect to deposit page or handle deposit logic
                response.sendRedirect("Deposit.html");
                break;
            case "withdrawal":
                // Redirect to withdrawal page or handle withdrawal logic
                response.sendRedirect("Withdraw.html");
                break;
            case "viewTransactions":
                // Redirect to transactions page or handle transactions logic
                response.sendRedirect("ViewTransaction.jsp");
                break;
            case "viewCurrentBalance":
                // Redirect to current balance page or handle balance logic
                response.sendRedirect("ViewBalance.jsp");
                break;
            case "closeAccount":
                // Redirect to close account page or handle close account logic
                response.sendRedirect("CloseAccount.jsp");
                break;
            case "logout":
                // Redirect to logout page or handle logout logic
                response.sendRedirect("Logout.html");
                break;
            default:
                response.sendRedirect("CustomerSuccess.html"); // Default to dashboard
                break;
        }
    }

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    doPost(request, response);
}
}

