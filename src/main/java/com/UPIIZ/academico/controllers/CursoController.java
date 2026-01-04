package com.UPIIZ.academico.controllers;

import com.UPIIZ.academico.entities.Curso;
import com.UPIIZ.academico.services.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cursos")
@Tag(name = "Cursos", description = "Gestión de cursos")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @Operation(
            summary = "Crear curso",
            description = "Registra un nuevo curso en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos del curso inválidos"),
            @ApiResponse(responseCode = "409", description = "Curso duplicado"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @PostMapping
    public ResponseEntity<Curso> crear(@RequestBody Curso curso) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.crear(curso));
    }

    @Operation(
            summary = "Listar cursos",
            description = "Obtiene la lista de todos los cursos registrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(
            summary = "Obtener curso",
            description = "Obtiene la información de un curso por su código"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso encontrado"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping("/{codigo}")
    public ResponseEntity<Curso> obtener(@PathVariable String codigo) {
        return ResponseEntity.ok(service.obtenerPorCodigo(codigo));
    }

    @Operation(
            summary = "Eliminar curso",
            description = "Elimina un curso del sistema por su código"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigo) {
        service.eliminar(codigo);
        return ResponseEntity.noContent().build();
    }
}
