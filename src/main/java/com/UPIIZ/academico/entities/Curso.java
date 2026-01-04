package com.UPIIZ.academico.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Curso {

    @Id
    private String codigoCurso;

    private String area;
    private String especialidad;
    private LocalDate inicio;
    private LocalDate fin;

    @ManyToOne
    @JoinColumn(name = "codigo_profesor")
    private Profesor profesor;

    @ManyToMany(mappedBy = "cursos")
    private List<Estudiante> estudiantes = new ArrayList<>();


    public Curso() {
    }

    public Curso(String codigoCurso, String area, String especialidad, LocalDate inicio, LocalDate fin, Profesor profesor) {
        this.codigoCurso = codigoCurso;
        this.area = area;
        this.especialidad = especialidad;
        this.inicio = inicio;
        this.fin = fin;
        this.profesor = profesor;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
