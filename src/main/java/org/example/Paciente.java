package org.example;

public class Paciente {
    private String cedula;
    private int n_historial;
    private String nombre;
    private String apellido;
    private String telefono;
    private int edad;
    private String descripcion;

    public Paciente() {
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getN_historial() {
        return n_historial;
    }

    public void setN_historial(int n_historial) {
        this.n_historial = n_historial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void imprimir() {
        System.out.println("Cedula: " + cedula);
        System.out.println("N° Historial clínico: " + n_historial);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Telefono: " + telefono);
        System.out.println("Edad: " + edad);
        System.out.println("Descripcion: " + descripcion);

    }
}
