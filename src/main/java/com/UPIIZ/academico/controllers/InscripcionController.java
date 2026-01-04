package com.UPIIZ.academico.controllers;

import com.UPIIZ.academico.entities.Curso;
import com.UPIIZ.academico.entities.Inscripcion;
import com.UPIIZ.academico.services.InscripcionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inscripciones")
@Tag(name = "Inscripciones", description = "Gestión de inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @Operation(
            summary = "Inscribir estudiante en curso",
            description = "Registra la inscripción de un estudiante a un curso"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recurso creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o incompletos"),
            @ApiResponse(responseCode = "409", description = "Conflicto (inscripción duplicada)"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @PostMapping("/estudiantes/{dui}/cursos/{codigoCurso}")
    public ResponseEntity<Inscripcion> inscribir(
            @PathVariable String dui,
            @PathVariable String codigoCurso) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inscripcionService.inscribir(dui, codigoCurso));
    }

    @Operation(
            summary = "Listar cursos de un estudiante",
            description = "Obtiene la lista de cursos en los que está inscrito un estudiante"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cursos obtenida correctamente"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping("/estudiantes/{dui}/cursos")
    public ResponseEntity<List<Curso>> cursosDeEstudiante(
            @PathVariable String dui) {

        return ResponseEntity.ok(
                inscripcionService.cursosPorEstudiante(dui)
        );
    }
}



