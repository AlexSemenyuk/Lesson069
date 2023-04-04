package org.itstep;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class HomeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req,
                           HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("""
                                <!DOCTYPE html>
                                <html lang="en">
                                <head>
                                <meta charset="UTF-8">
                                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title>Task01</title>
                                <link rel="stylesheet" href="https://unpkg.com/@picocss/pico@1.*/css/pico.min.css">
                                <style>
                                    :root {
                                        /* text color */
                                        --color-text-primary: rgba(0, 0, 0, 1);
                                        --color-text-secondary: rgba(255, 255, 255, 1);

                                        /* background */
                                        --color-background-main: rgb(210, 210, 210, 1);
                                        --color-background-aside: rgb(230, 230, 230, 1);

                                        /* text */
                                        --text-primary: "Arial", sans-serif;
                                    }

                                    body {
                                         color: var(--color-text-primary);                                   
                                     }
                                     main {
                                         background-color: var(--color-background-main);
                                         display: grid;
                                         grid-template-columns: 30% 40% 30%;
                                     }
                                     div {
                                        margin-top: 10%;                                    
                                     }
                                     .menu {
                                        border: 1px solid var(--color-text-primary);
                                        border-radius: 5px;
                                        text-align: center;
                                     }                                     
                                     h1.h1 {
                                        margin: 20px;
                                        text-align: center;
                                        font-size: 20px;
                                     }       
                                     .button {
                                        margin: 20px 5% 20px 5%;
                                        width: 90%;
                                     } 
                                </style>
                            </head>
                            <body>
                                <main>
                                    <div></div>
                                    <div class="menu">
                                        <h1 class="h1">Hello, welcome you to our application</h1>
                                        <form action="signin">
                                              <button class="button" type="submit">Sign in</button>
                                        </form>
                                        <form action="registration">
                                              <button class="button" type="submit">Registration</button>
                                        </form>                                   
                                    </div>
                                    <div></div>
                                </main>
                            <body>
                            </html>


                """);

    }
}


