package Estructuras;

import modelo.Paciente;

public class ColaPacientes {

    private NodoCola frente;
    private NodoCola fin;

    public ColaPacientes() {

        frente = null;
        fin = null;
    }
    
    public void encolar(Paciente paciente) {
        NodoCola nuevo = new NodoCola(paciente);
        if (frente == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }
    public Paciente desencolar() {
        if (frente == null) {
            return null;
        }

        Paciente paciente = frente.paciente;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        return paciente;
    }
    
    public Paciente verSiguiente() {

        if (frente == null) {
            return null;
        }
        return frente.paciente;
    }
    public void mostrarCola() {
        if (frente == null) {
            System.out.println("No hay pacientes en espera.");
            return;
        }
        NodoCola aux = frente;
        System.out.println("\n===== COLA DE ATENCIÓN =====");
        while (aux != null) {
            System.out.println("Nombre: " + aux.paciente.getNombres());
            System.out.println("DNI: " + aux.paciente.getDni());
            System.out.println("Gravedad: " + aux.paciente.getGravedad());
            System.out.println("---------------------------");

            aux = aux.siguiente;
        }
     }
}
    
