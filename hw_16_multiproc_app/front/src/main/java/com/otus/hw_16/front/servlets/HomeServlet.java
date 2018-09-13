package com.otus.hw_16.front.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.otus.hw_16.front.servlets.SharedConstants.DEFAULT_VISITOR;

@WebServlet(name = "HomeServlet", urlPatterns = { "", "/home" })
public class HomeServlet extends HttpServlet {

    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");
        req.setAttribute("visitor", username != null ? username : DEFAULT_VISITOR);
        resp.setContentType("text/html;charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        req.getRequestDispatcher("/index.ftl").forward(req, resp);
    }

}
