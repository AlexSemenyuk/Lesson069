package org.itstep;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class TryAgainServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req,
                           HttpServletResponse resp) throws ServletException, IOException {

//        String login = req.getParameter("login");
//        String password = req.getParameter("password");

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("""
                    <img src="resources/try-again.jpg">      
                """);
    }
}
