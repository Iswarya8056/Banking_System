package com.banking;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/PrintTransactionServlet")
public class PrintTransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("account_number") == null) {
            response.sendRedirect("CustomerLogin.html");
            return;
        }

        String accountNumber = (String) session.getAttribute("account_number");
        List<Transaction> transactions = getTransactions(accountNumber);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=TransactionHistory.pdf");

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            document.add(new Paragraph("Transaction History", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD)));
            document.add(new Paragraph(" ")); // Add a blank line

            PdfPTable table = new PdfPTable(5); // 5 columns
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            Font headFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            // Table headers
            addTableHeader(table, headFont);

            // Table rows
            for (Transaction transaction : transactions) {
                addTableRow(table, transaction);
            }

            document.add(table);
            document.close();
        } catch (DocumentException ex) {
            throw new IOException(ex);
        }
    }

    private List<Transaction> getTransactions(String accountNumber) {
        List<Transaction> transactions = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/db3";
        String dbUsername = "root";
        String dbPassword = "Iswarya@1910";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM transaction WHERE account_number = ? ORDER BY transaction_date DESC")) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            pst.setString(1, accountNumber);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(rs.getInt("transaction_id"));
                    transaction.setAccountNumber(rs.getString("account_number"));
                    transaction.setAmount(rs.getDouble("transaction_amount"));
                    transaction.setType(rs.getString("transaction_type"));
                    transaction.setDate(rs.getTimestamp("transaction_date"));
                    transactions.add(transaction);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactions;
    }

    private void addTableHeader(PdfPTable table, Font headFont) {
        PdfPCell hcell;
        hcell = new PdfPCell(new Phrase("Transaction ID", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Account Number", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Amount", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Type", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Date", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
    }

    private void addTableRow(PdfPTable table, Transaction transaction) {
        PdfPCell cell;

        cell = new PdfPCell(new Phrase(String.valueOf(transaction.getTransactionId())));
        cell.setPaddingLeft(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(transaction.getAccountNumber()));
        cell.setPaddingLeft(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(String.valueOf(transaction.getAmount())));
        cell.setPaddingLeft(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(transaction.getType()));
        cell.setPaddingLeft(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(transaction.getDate().toString()));
        cell.setPaddingLeft(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    private class Transaction {
        private int transactionId;
        private String accountNumber;
        private double amount;
        private String type;
        private java.sql.Timestamp date;

        // Getters and Setters for all fields
        public int getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(int transactionId) {
            this.transactionId = transactionId;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public java.sql.Timestamp getDate() {
            return date;
        }

        public void setDate(java.sql.Timestamp date) {
            this.date = date;
        }
    }
}
