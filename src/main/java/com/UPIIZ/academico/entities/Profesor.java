package com.UPIIZ.academico.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Profesor {

    @Id
    private String codigoProfesor;

    private String nombre;
    private String correo;
    private String especialidad;

    public Profesor() {
    }

    // Constructor con parámetros (opcional, pero correcto)
    public Profesor(String codigoProfesor, String nombre, String correo, String especialidad) {
        this.codigoProfesor = codigoProfesor;
        this.nombre = nombre;
        this.correo = correo;
        this.especialidad = especialidad;
    }

    public String getCodigoProfesor() {

        return codigoProfesor;
    }

    public void setCodigoProfesor(String codigoProfesor) {
        this.codigoProfesor = codigoProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
