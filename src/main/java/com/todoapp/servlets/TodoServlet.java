package com.todoapp.servlets;

import com.todoapp.dao.TodoDaoImpl;
import com.todoapp.model.Todo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TodoServlet", value = "/user/todo")
public class TodoServlet extends HttpServlet {

    private TodoDaoImpl todoDao;

    @Override
    public void init() {
        todoDao = new TodoDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute("username");
        List<Todo> todos = todoDao.selectAllTodos(username);
        request.setAttribute("todos", todos);
        request.getRequestDispatcher("/todo.jsp").forward(request, response);
    }
}
