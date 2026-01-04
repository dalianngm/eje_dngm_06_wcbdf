package com.UPIIZ.academico.services;

import com.UPIIZ.academico.entities.Curso;
import com.UPIIZ.academico.entities.Profesor;
import com.UPIIZ.academico.repositories.CursoRepository;
import com.UPIIZ.academico.repositories.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final ProfesorRepository profesorRepository;

    public CursoService(CursoRepository cursoRepository,
                        ProfesorRepository profesorRepository) {
        this.cursoRepository = cursoRepository;
        this.profesorRepository = profesorRepository;
    }

    public Curso crear(Curso curso) {
        // Validar que el profesor exista
        String codigoProfesor = curso.getProfesor().getCodigoProfesor();
        Profesor profesor = profesorRepository.findById(codigoProfesor)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        curso.setProfesor(profesor);
        return cursoRepository.save(curso);
    }

    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    public Curso obtenerPorCodigo(String codigo) {
        return cursoRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    public void eliminar(String codigo) {
        cursoRepository.deleteById(codigo);
    }
}
