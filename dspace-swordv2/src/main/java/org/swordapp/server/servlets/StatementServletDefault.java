package org.swordapp.server.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.swordapp.server.StatementAPI;
import org.swordapp.server.StatementManager;

public class StatementServletDefault extends SwordServlet {
    private StatementManager sm;
    private StatementAPI statementApi;

    public void init() throws ServletException {
        super.init();

        // load the container manager implementation
        this.sm = (StatementManager) this.loadImplClass("statement-impl", false);

        // initialise the underlying servlet processor
        this.statementApi = new StatementAPI(this.sm, this.config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        this.statementApi.get(req, resp);
    }
}
