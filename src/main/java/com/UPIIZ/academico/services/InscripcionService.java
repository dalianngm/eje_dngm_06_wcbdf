package com.UPIIZ.academico.services;

import com.UPIIZ.academico.entities.Curso;
import com.UPIIZ.academico.entities.Estudiante;
import com.UPIIZ.academico.entities.Inscripcion;
import com.UPIIZ.academico.repositories.CursoRepository;
import com.UPIIZ.academico.repositories.EstudianteRepository;
import com.UPIIZ.academico.repositories.InscripcionRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InscripcionService {

    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;
    private final InscripcionRepository inscripcionRepository;

    public InscripcionService(EstudianteRepository estudianteRepository,
                              CursoRepository cursoRepository,
                              InscripcionRepository inscripcionRepository) {
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    // =====================================================
    // INSCRIBIR estudiante en un curso (N:M)
    // =====================================================
    public Inscripcion inscribir(String dui, String codigoCurso) {

        // Buscar estudiante
        Estudiante estudiante = estudianteRepository.findById(dui)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        // Buscar curso
        Curso curso = cursoRepository.findById(codigoCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        // Crear inscripción
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEstudiante(estudiante);
        inscripcion.setCurso(curso);

        return inscripcionRepository.save(inscripcion);
    }

    // =====================================================
    // OBTENER cursos de un estudiante
    // =====================================================
    public List<Curso> cursosPorEstudiante(String dui) {

        Estudiante estudiante = estudianteRepository.findById(dui)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        return inscripcionRepository.findByEstudiante(estudiante)
                .stream()
                .map(Inscripcion::getCurso)
                .toList();
    }
}
