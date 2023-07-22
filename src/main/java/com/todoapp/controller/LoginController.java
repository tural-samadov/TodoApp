package com.todoapp.controller;

import com.todoapp.dao.UserDaoImpl;
import com.todoapp.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    private UserDaoImpl userDao;

    @Override
    public void init() {
        userDao = new UserDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkUser(request, response);
    }

    private void checkUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDao.getUser(username, password);
        if (user != null) {
            request.getSession().setAttribute("name", user.getName());
            request.getSession().setAttribute("username", user.getUsername());
            response.sendRedirect("user/todo");
        } else {
            request.setAttribute("loginError", "Username or Password id incorrect!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
