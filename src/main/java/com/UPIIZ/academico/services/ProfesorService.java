package com.UPIIZ.academico.services;

import com.UPIIZ.academico.entities.Profesor;
import com.UPIIZ.academico.repositories.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {

    private final ProfesorRepository repository;

    public ProfesorService(ProfesorRepository repository) {
        this.repository = repository;
    }

    public Profesor crear(Profesor profesor) {
        return repository.save(profesor);
    }

    public Profesor actualizar(String codigo, Profesor profesorActualizado) {

        Profesor profesorExistente = repository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        profesorExistente.setNombre(profesorActualizado.getNombre());
        profesorExistente.setEspecialidad(profesorActualizado.getEspecialidad());
        profesorExistente.setCorreo(profesorActualizado.getCorreo());

        return repository.save(profesorExistente);
    }


    public List<Profesor> listar() {
        return repository.findAll();
    }

    public Profesor obtenerPorCodigo(String codigo) {
        return repository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
    }

    public void eliminar(String codigo) {
        repository.deleteById(codigo);
    }
}