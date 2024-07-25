package com.banking;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.ResetDAO;

@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("account_number");
        
        if (accountNumber == null) {
            response.sendRedirect("CustomerLogin.html");
        } else {
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("account_number");

        if (accountNumber == null) {
            response.sendRedirect("CustomerLogin.html");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "New passwords do not match.");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
            return;
        }

        ResetDAO resetDAO = new ResetDAO();

        try {
            if (!resetDAO.validateCurrentPassword(accountNumber, currentPassword)) {
                request.setAttribute("error", "Current password is incorrect.");
                request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
                return;
            }

            boolean updated = resetDAO.updatePassword(accountNumber, newPassword);
            if (updated) {
                response.sendRedirect("passwordResetSuccess.html");
            } else {
                request.setAttribute("error", "Error updating password. Please try again.");
                request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
        }
    }
}
