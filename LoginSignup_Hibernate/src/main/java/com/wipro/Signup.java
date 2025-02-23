package com.wipro;

import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wipro.model.User;
import com.wipro.util.HibernateUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Signup")
public class Signup extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String country = request.getParameter("country");
        int age = Integer.parseInt(request.getParameter("age"));

        User user = new User(name, email, password, age, country);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }

        RequestDispatcher rsd = request.getRequestDispatcher("/signupWelcome.jsp");
        rsd.forward(request, response);
    }
}
