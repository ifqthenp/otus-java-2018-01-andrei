package com.otus.hw_12.servlets;

import com.otus.hw_12.util.ehcache.EhCacheUtil;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AdminServlet extends HttpServlet
{
    private static final String DEFAULT_USER_NAME = "UNKNOWN";
    private static final String ADMIN_PAGE_TEMPLATE = "admin.html";
    private static final String ADMIN_CACHE_PAGE_TEMPLATE = "admin_cache.html";

    private final TemplateProcessor templateProcessor;

    @SuppressWarnings("WeakerAccess")
    public AdminServlet(TemplateProcessor templateProcessor)
    {
        this.templateProcessor = templateProcessor;
    }

    @SuppressWarnings("WeakerAccess")
    public AdminServlet() throws IOException
    {
        this(new TemplateProcessor());
    }

    private static Map<String, Object> getCacheStats()
    {
        final Map<String, Object> result = new LinkedHashMap<>();
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        try {
            final String mBeanObjectName = EhCacheUtil.getCacheStatsObjectName();
            final ObjectName objectName = new ObjectName(mBeanObjectName);
            if (mbs.isRegistered(objectName)) {
                MBeanAttributeInfo[] cacheInfo = mbs.getMBeanInfo(objectName).getAttributes();
                for (final MBeanAttributeInfo mBeanAttributeInfo : cacheInfo) {
                    final String mBeanAttributeDescription = mBeanAttributeInfo.getDescription();
                    result.put(mBeanAttributeDescription, mbs.getAttribute(objectName, mBeanAttributeDescription));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return result;
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
        String login = (String) request.getSession().getAttribute("login");
        pageVariables.put("login", login != null ? login : DEFAULT_USER_NAME);

        return pageVariables;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String page;
        if (request.getSession().getAttribute("login") != null && request.getSession().getAttribute("password") != null) {
            Map<String, Object> cacheStats = getCacheStats();
            page = templateProcessor.getPage(ADMIN_CACHE_PAGE_TEMPLATE, cacheStats);
        } else {
            Map<String, Object> pageVariables = createPageVariablesMap(request);
            page = templateProcessor.getPage(ADMIN_PAGE_TEMPLATE, pageVariables);
        }

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(page);
    }
}
