package org.itstep;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req,
                           HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("""              
                    <link rel="stylesheet" href="https://unpkg.com/@picocss/pico@1.*/css/pico.min.css">
                    <style>
                        :root {
                            /* text color */
                            --color-text-primary: rgba(0, 0, 0, 1);
                            --color-text-secondary: rgba(255, 255, 255, 1);
                        
                            /* background */
                            --color-background-main: rgb(210, 210, 210, 1);
                            --color-background-header: rgb(170, 170, 170, 1);
                           
                            /* text */
                            --text-primary: "Arial", sans-serif;                      
                        }                      
                        body {
                             color: var(--color-text-primary);
                             background-color: var(--color-background-main);
                        }                    
                        main.main {                             
                             display: grid;
                             grid-template-columns: 30% 40% 30%;
                             padding: 30px 0px 30px 0px;
                        }                        
                        .form {
                            margin: 10px;
                        }
                        h1.h1 {
                            margin: 20px;
                            text-align: center;
                            font-size: 20px;
                        }      
                        .reg {
                             display: grid;
                             grid-template-columns: 25% 75%;
                             margin-top: 20px;
                        }                         
                        label {
                             display: inline-block;                       
                        }
                        input {
                             display: inline-block;
                             background-color: var(--color-text-secondary);
                        }                         
                         #login, #password {
                             text-indent: 1em;
                             margin: 0px;
                             padding: 0px;
                         }
                         .but-submit {
                             margin-top: 20px;
                         }
                         .label-input {
                            margin: 0px;
                            line-height: 59px
                         }
                         .header {
                            padding: 0px;                                                       
                         }
                         .but-home {
                            width: 15%;
                            margin: 0px;
                            margin: 10px 0px 10px 0px;
                         }
                         button.button {
                            margin: 10px;
                            font-size: 14px;
                            padding: 5px;                          
                         }
                         .total-success {
                            font-size: 20px;
                            text-align: center;
                            color: blue;
                         }
                         .total-try {
                            font-size: 20px;
                            text-align: center;
                            color: red;
                         }
                    </style>                       
                    <header class="header">
                       <form class="but-home" action="home">
                            <button class="button" type="submit">Home page</button>
                       </form>                 
                    </header>
                    <main class="main">
                        <div>
                        </div>
                        <div class="menu">
                            <h1 class="h1">Registration</h1>
                            <form method="POST" class="form">
                                <div class="reg">
                                    <label class="label-input" for="login">Login</label>              
                                    <input id="login" type="text" name="login" placeholder="Input your login"/>
                                </div>
                                <div class="reg">
                                    <label class="label-input" for="password">Password</label>
                                    <input id="password" type="text" name="password" placeholder="Input your password"/>
                                </div>
                                <div class="reg">
                                    <label class="label-input" for="fullname">Full name</label>
                                    <input id="fullname" type="text" name="fullname" placeholder="Input your full name"/>
                                </div>                            
                                <input class="but-submit" type="submit" value="Submit"/>
                            </form>                                                            
                        </div>
                        <div>
                        </div>
                    </main>           
                """);

        System.out.println("login = " + login);
        System.out.println("password = " + password);
        System.out.println("fullname = " + fullname);
        int[] checkProgress = new int[1];
        checkProgress[0] = 0;

        if (login != null && !login.isBlank() && password != null && !password.isBlank() &&
            fullname != null && !fullname.isBlank()) {

            List<User> usersFromFile = null;

            User tmp = null;
            File file1 = new File("e:\\JAVA\\Projects\\GitHub Homeworks\\Lesson069\\src\\main\\webapp\\resources\\users.db");    // не работает "Lesson069/resources/users.db"
            System.out.println(file1.exists());
            if (file1.exists()) {
                // Считывание с файла
                try (InputStream in = new FileInputStream(file1);
                     ObjectInputStream objectInput = new ObjectInputStream(in)) {
                    usersFromFile = (List<User>) objectInput.readObject();

                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            List<User> newUsers = new ArrayList<>(usersFromFile.size() + 1);
            System.out.println("Full list of users before change");
            for (int i = 0; i < usersFromFile.size(); i++) {
                newUsers.add(usersFromFile.get(i));
                System.out.println(usersFromFile.get(i).toString());
            }
//                usersFromFile.stream().forEach(user -> {
////                    newUsers.add(user);
//                    System.out.println(user.toString());
//                });

            // Добавление в List нового user
            System.out.println("usersFromFile.size() = " + usersFromFile.size());
            tmp = new User(login, password, fullname);
            System.out.println("tmp = " + tmp);
//            usersFromFile.add(tmp);
//            usersFromFile.get(usersFromFile.size() + 1) = tmp;
            newUsers.add(tmp);
            System.out.println("newUsers.size() = " + newUsers.size());

            System.out.println("Full list of users after change");
            newUsers.stream().forEach(user -> {
                System.out.println(user.toString());
            });

            // Запись объекта в файл (Сериализация)
            try (OutputStream out = new FileOutputStream(file1);
                 ObjectOutputStream objectOut = new ObjectOutputStream(out)) {

                objectOut.writeObject(newUsers);
                checkProgress[0] = 1;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Вывод сообщения
            System.out.println("checkProgress = " + checkProgress[0]);
            if (checkProgress[0] == 1) {
                writer.println("""
                        <p class="total-success">Add user is SUCCESSFUL</p>
                        """);
            } else {
                writer.println("""
                        <p class="total-try">Add user is NOT SUCCESSFUL</p>
                        """);
            }
        }

    }
}

//<!DOCTYPE html>
//<html lang="en">
//<head>
//<meta charset="UTF-8">
//<meta http-equiv="X-UA-Compatible" content="IE=edge">
//<meta name="viewport" content="width=device-width, initial-scale=1.0">
//<title>Task01</title>

//</head>
//<body>

//<body>
//</html>