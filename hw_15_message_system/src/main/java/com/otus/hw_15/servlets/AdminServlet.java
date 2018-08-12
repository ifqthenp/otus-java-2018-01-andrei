package com.otus.hw_15.servlets;

import com.otus.hw_15.util.mbeans.MBeansUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.otus.hw_15.servlets.SharedConstants.DEFAULT_PASSWORD;
import static com.otus.hw_15.servlets.SharedConstants.DEFAULT_VISITOR;

@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet
{
    public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        final HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("username");
        Map<String, Object> stats;

        if (userName != null && session.getAttribute("password").equals(DEFAULT_PASSWORD)) {
            stats = MBeansUtil.getEhCacheStats("userDataSetCache");
        } else {
            stats = createPageVariablesMap(req);
        }

        req.setAttribute("stats", stats);
        req.setAttribute("visitor", userName != null ? userName : DEFAULT_VISITOR);
        setOK(resp);
        req.getRequestDispatcher("/tml/admin.ftl").forward(req, resp);
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request)
    {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("locale", request.getLocale());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());

        //let's get login from session
        String username = (String) request.getSession().getAttribute("username");
        pageVariables.put("username", username != null ? username : DEFAULT_VISITOR);

        return pageVariables;
    }

    private void setOK(final HttpServletResponse resp)
    {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
