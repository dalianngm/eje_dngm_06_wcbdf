package com.UPIIZ.academico.repositories;

import com.UPIIZ.academico.entities.Estudiante;
import com.UPIIZ.academico.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByEstudiante(Estudiante estudiante);
}
