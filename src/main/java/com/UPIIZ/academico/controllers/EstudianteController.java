package com.UPIIZ.academico.controllers;

import com.UPIIZ.academico.entities.Estudiante;
import com.UPIIZ.academico.services.EstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Estudiantes",
        description = "Operaciones relacionadas con estudiantes"
)
@RestController
@RequestMapping("/api/v1/estudiantes")
public class EstudianteController {

    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    @Operation(
            summary = "Crear estudiante",
            description = "Registra un nuevo estudiante en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estudiante creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos del estudiante inválidos"),
            @ApiResponse(responseCode = "409", description = "Estudiante duplicado"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @PostMapping
    public ResponseEntity<Estudiante> crear(@RequestBody Estudiante estudiante) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.crear(estudiante));
    }

    @Operation(
            summary = "Listar estudiantes",
            description = "Obtiene la lista de todos los estudiantes registrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping
    public ResponseEntity<List<Estudiante>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(
            summary = "Obtener estudiante",
            description = "Obtiene la información de un estudiante por su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recurso encontrado"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido"),
            @ApiResponse(responseCode = "404", description = "No encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    @GetMapping("/{dui}")
    public ResponseEntity<Estudiante> obtener(@PathVariable String dui) {
        return ResponseEntity.ok(service.obtenerPorDui(dui));
    }

    @Operation(
            summary = "Actualizar estudiante",
            description = "Actualiza la información de un estudiante existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recurso actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
            @ApiResponse(responseCode = "409", description = "Conflicto de datos"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizarEstudiante(
            @PathVariable Long id,
            @RequestBody Estudiante estudiante
    ) {
        return ResponseEntity.ok(estudiante);
    }

    @Operation(
            summary = "Eliminar estudiante",
            description = "Elimina un estudiante por su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eliminación exitosa"),
            @ApiResponse(responseCode = "204", description = "Eliminación sin contenido"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @DeleteMapping("/{dui}")
    public ResponseEntity<Void> eliminar(@PathVariable String dui) {
        service.eliminar(dui);
        return ResponseEntity.noContent().build();
    }
}

