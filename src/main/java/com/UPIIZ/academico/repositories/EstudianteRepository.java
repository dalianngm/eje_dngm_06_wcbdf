package com.UPIIZ.academico.repositories;

import com.UPIIZ.academico.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, String> {}
