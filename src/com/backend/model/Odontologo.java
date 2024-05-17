package com.backend.model;

public class Odontologo {
    private int id;
    private String nombre;
    private String apellido;
    private int numeroMatricula;

    public Odontologo(int id, String nombre, String apellido, int numeroMatricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroMatricula = numeroMatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public String getApellido() {
        return apellido;
    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    @Override

    public String toString(){
        return "Id: " + id + "- Nombre: " + nombre + "- Apellido " + apellido + "-Numero de matricula: " + numeroMatricula;
    }
}



