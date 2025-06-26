package com.example.TMF687.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>TMF687 Product Stock Backend API</title>
                    <style>
                        body {
                            background: linear-gradient(to right, #6dd5fa, #2980b9);
                            color: #ffffff;
                            font-family: Arial, sans-serif;
                            text-align: center;
                            padding: 50px;
                        }
                        h1 {
                            font-size: 3em;
                            margin-bottom: 10px;
                        }
                        p {
                            font-size: 1.2em;
                        }
                        .api-endpoints {
                            margin-top: 30px;
                            background-color: rgba(255, 255, 255, 0.1);
                            padding: 20px;
                            border-radius: 10px;
                            display: inline-block;
                            text-align: left;
                        }
                        a {
                            color: #ffffcc;
                            text-decoration: underline;
                        }
                    </style>
                </head>
                <body>
                    <h1>Welcome to TMF687 Product Stock Backend API</h1>
                    <p>This backend provides TMF-compliant REST APIs for managing Product Stock, Reserve Stock, and Check Stock operations.</p>
                    
                    <div class="api-endpoints">
                        <h2>Available API Endpoints:</h2>
                        <ul>
                            <li><a href="/tmf-api/stock/v4/productStock">GET /productStock</a></li>
                            <li><a href="/tmf-api/stock/v4/reserveProductStock">GET /reserveProductStock</a></li>
                            <li><a href="/tmf-api/stock/v4/checkProductStock">GET /checkProductStock</a></li>
                        </ul>
                        <p>Use Postman or Swagger to explore full API capabilities.</p>
                    </div>

                    <p>Created by: <strong>Your Name (Team TMF687)</strong></p>
                </body>
                </html>
                """;
    }
}

