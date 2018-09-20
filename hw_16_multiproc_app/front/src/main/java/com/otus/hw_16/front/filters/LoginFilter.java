package com.otus.hw_16.front.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.otus.hw_16.front.servlets.SharedConstants.DEFAULT_PASSWORD;

@WebFilter(
        filterName = "loginFilter",
        servletNames = "AdminServlet",
        dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.ASYNC }
)
public class LoginFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        final HttpSession session = ((HttpServletRequest) request).getSession(false);
        final String userName = (String) session.getAttribute("username");
        final String password = (String) session.getAttribute("password");

        final boolean usernameIsNull = userName == null;
        final boolean passwordIsNull = password == null;
        final boolean credentialsAreNull = usernameIsNull && passwordIsNull;
        final boolean passwordIsNotValid = !passwordIsNull && !password.equals(DEFAULT_PASSWORD);

        if (credentialsAreNull || passwordIsNotValid) {
            ((HttpServletResponse) response).sendRedirect("login");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
