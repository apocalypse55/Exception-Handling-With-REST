package com.example.bookstore.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

import java.util.List;




@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Shardool Tripathi",
                        email = "shardooltripathiwork@gmail.com",
                        url = "www.shardooltripathi.com.np"
                ),
                description = "These API are for books.",
                title = "Test Point for API",
                version = "1.0"
        ),
        servers = {@Server(description = "Bookstore Environment",url = "http://192.168.1.151:8082"),
        }
)
public class OpenAPIConfig {

}