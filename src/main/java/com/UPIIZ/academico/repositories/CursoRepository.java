package com.UPIIZ.academico.repositories;

import com.UPIIZ.academico.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, String> {}
