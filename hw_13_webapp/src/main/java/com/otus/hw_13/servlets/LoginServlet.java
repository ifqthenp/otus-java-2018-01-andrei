package com.otus.hw_13.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.otus.hw_13.servlets.SharedConstants.DEFAULT_PASSWORD;
import static com.otus.hw_13.servlets.SharedConstants.DEFAULT_VISITOR;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final String username = req.getParameter("username");
        final String password = req.getParameter("password");

        if (username != null && password.equals(DEFAULT_PASSWORD)) {
            saveToSession(req, username, password);
        }

        String userFromSession = (String) req.getSession().getAttribute("username");
        req.setAttribute("visitor", userFromSession != null ? userFromSession : DEFAULT_VISITOR);
        setOK(resp);
        req.getRequestDispatcher("/tml/login.ftl").forward(req, resp);
    }

    private void saveToSession(final HttpServletRequest req, final String requestLogin, final String requestPassword)
    {
        HttpSession session = req.getSession();
        session.setAttribute("username", requestLogin);
        session.setAttribute("password", requestPassword);
    }

    private void setOK(final HttpServletResponse resp) {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
