package org.swordapp.server.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.swordapp.server.SwordConfiguration;

public class SwordServlet extends HttpServlet {
    private final Logger log = LogManager.getLogger();

    protected SwordConfiguration config;

    public void init() throws ServletException {
        // load the configuration implementation
        this.config = (SwordConfiguration) this.loadImplClass("config-impl", false);
    }

    protected Object loadImplClass(String paramName, boolean allowNull)
        throws ServletException {
        String className = getServletContext().getInitParameter(paramName);
        if (className == null) {
            if (allowNull) {
                return null;
            } else {
                log.fatal("'" + paramName + "' init parameter not set in Servlet context");
                throw new ServletException("'" + paramName + "' init parameter not set in Servlet context");
            }
        } else {
            try {
                Object obj = Class.forName(className).newInstance();
                log.info("Using " + className + " as '" + paramName + "'");
                return obj;
            } catch (Exception e) {
                log.fatal("Unable to instantiate class from '" + paramName + "': " + className);
                throw new ServletException("Unable to instantiate class from '" + paramName + "': " + className, e);
            }
        }
    }
}
