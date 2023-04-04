package org.itstep;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class SuccessServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req,
                           HttpServletResponse resp) throws ServletException, IOException {

//        String login = req.getParameter("login");
//        String password = req.getParameter("password");

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
//        Files.list(Path.of("."))
//                .forEach(path -> {
//                    System.out.println((Files.isDirectory(path) ? "DIR" : "File") + " " + path);
//                });

        writer.println("""        
                    <img src="resources/success.jpg">                   
                """);
    }
}
