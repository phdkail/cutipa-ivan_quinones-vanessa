package com.backend.model;

public class Odontologo {
    private String id;
    private String nombre;
    private String apellido;

    public Odontologo(String id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(int id) {
        this.id = String.valueOf(id);
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    @Override

    public String toString(){
        return "Id: " + id + "- Nombre: " + nombre + "- Apellido " + apellido + "-Numero de matricula: ";
    }
}



