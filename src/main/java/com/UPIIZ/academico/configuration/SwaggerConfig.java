package com.UPIIZ.academico.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.*;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "API Académico",
                description = "Documentación de la API del sistema académico",
                version = "1.1.0",
                termsOfService = "https://upiiz.ipn.mx/terminos",
                contact = @Contact(
                        name = "Dalia Naomi García Macías",
                        email = "dgarciam1902@alumno.ipn.mx",
                        url = "https://upiiz.ipn.mx"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8081", description = "Servidor local"),
                @Server(url = "https://tu-proyecto.onrender.com", description = "Servidor Render")
        }
)
public class SwaggerConfig {
}

