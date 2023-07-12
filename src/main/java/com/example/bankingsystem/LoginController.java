package com.example.bankingsystem;

import jakarta.servlet.http.HttpServlet;
import com.example.bankingsystem.database;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        int phone = Integer.parseInt(request.getParameter("phone"));
        String password = request.getParameter("password");


        // we push the data to the database
        database.addCustomer(id, name, address, email, phone, password);

        // Redirect to a success page or display a message
        response.sendRedirect("success.jsp"); // Replace "success.jsp" with the appropriate URL or page name
    }
}
