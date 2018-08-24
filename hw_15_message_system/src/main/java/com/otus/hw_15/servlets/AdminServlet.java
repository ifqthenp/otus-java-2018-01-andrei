package com.otus.hw_15.servlets;

import com.otus.hw_15.services.frontendService.websocket.MsgWebSocketCreator;
import com.otus.hw_15.services.frontendService.FrontendService;
import com.otus.hw_15.util.mbeans.MBeansUtil;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.otus.hw_15.servlets.SharedConstants.DEFAULT_PASSWORD;
import static com.otus.hw_15.servlets.SharedConstants.DEFAULT_VISITOR;

@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends WebSocketServlet
{
    private FrontendService frontendService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext applicationContext = (ClassPathXmlApplicationContext) config.getServletContext().getAttribute("applicationContext");
        this.frontendService = (FrontendService) applicationContext.getBean("frontendService");
        super.init(config);
    }

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

    @Override
    public void configure(final WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.getPolicy().setIdleTimeout(1_000_000);
        webSocketServletFactory.setCreator(new MsgWebSocketCreator(this.frontendService));
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
