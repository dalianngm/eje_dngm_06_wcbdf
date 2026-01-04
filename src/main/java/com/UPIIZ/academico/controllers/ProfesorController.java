package com.UPIIZ.academico.controllers;

import com.UPIIZ.academico.entities.Profesor;
import com.UPIIZ.academico.services.ProfesorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profesores")
@Tag(name = "Profesores", description = "Gestión de profesores")
public class ProfesorController {

    private final ProfesorService service;

    public ProfesorController(ProfesorService service) {
        this.service = service;
    }

    @Operation(
            summary = "Crear profesor",
            description = "Registra un nuevo profesor en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Profesor creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos del profesor inválidos"),
            @ApiResponse(responseCode = "409", description = "Profesor duplicado"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @PostMapping
    public ResponseEntity<Profesor> crear(@RequestBody Profesor profesor) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.crear(profesor));
    }

    @Operation(
            summary = "Actualizar profesor",
            description = "Actualiza la información de un profesor existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profesor actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos del profesor inválidos"),
            @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
            @ApiResponse(responseCode = "409", description = "Conflicto de datos"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @PutMapping("/{codigo}")
    public ResponseEntity<Profesor> actualizar(
            @PathVariable String codigo,
            @RequestBody Profesor profesor
    ) {
        return ResponseEntity.ok(
                service.actualizar(codigo, profesor)
        );
    }

    @Operation(
            summary = "Listar profesores",
            description = "Obtiene la lista de todos los profesores registrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping
    public ResponseEntity<List<Profesor>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(
            summary = "Obtener profesor",
            description = "Obtiene la información de un profesor por su código"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profesor encontrado"),
            @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping("/{codigo}")
    public ResponseEntity<Profesor> obtener(@PathVariable String codigo) {
        return ResponseEntity.ok(service.obtenerPorCodigo(codigo));
    }

    @Operation(
            summary = "Eliminar profesor",
            description = "Elimina un profesor del sistema por su código"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Profesor eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigo) {
        service.eliminar(codigo);
        return ResponseEntity.noContent().build();
    }
}
