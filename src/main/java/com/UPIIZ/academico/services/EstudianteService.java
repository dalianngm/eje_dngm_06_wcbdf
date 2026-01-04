package com.UPIIZ.academico.services;

import com.UPIIZ.academico.entities.Curso;
import com.UPIIZ.academico.entities.Estudiante;
import com.UPIIZ.academico.repositories.CursoRepository;
import com.UPIIZ.academico.repositories.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    public EstudianteService(EstudianteRepository estudianteRepository,
                             CursoRepository cursoRepository) {
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
    }

    // CREAR ESTUDIANTE
    public Estudiante crear(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    // LISTAR ESTUDIANTES
    public List<Estudiante> listar() {
        return estudianteRepository.findAll();
    }

    // OBTENER ESTUDIANTE POR DUI
    public Estudiante obtenerPorDui(String dui) {
        return estudianteRepository.findById(dui)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    // ELIMINAR ESTUDIANTE
    public void eliminar(String dui) {
        estudianteRepository.deleteById(dui);
    }

    public void inscribirCurso(String dui, String codigoCurso) {

        Estudiante estudiante = estudianteRepository.findById(dui)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Curso curso = cursoRepository.findById(codigoCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        estudiante.getCursos().add(curso);
        estudianteRepository.save(estudiante);
    }
}

