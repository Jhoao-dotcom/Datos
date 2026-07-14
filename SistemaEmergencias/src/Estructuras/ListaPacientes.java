package Estructuras;

import modelo.Paciente;

public class ListaPacientes {

    private Nodo inicio;

    public ListaPacientes() {
        inicio = null;
    }

	public void agregarPaciente(modelo.Paciente paciente) {
	
	    Nodo nuevo = new Nodo(paciente);
	
	    if (inicio == null) {
	        inicio = nuevo;
	    } else {
	
	        Nodo aux = inicio;
	
	        while (aux.siguiente != null) {
	            aux = aux.siguiente;
	        }
	
	        aux.siguiente = nuevo;
	    }
	
	}
	
	public void mostrarPacientes() {

	    if (inicio == null) {

	        System.out.println("No hay pacientes registrados.");
	        return;

	    }

	    Nodo aux = inicio;

	    while (aux != null) {

	        System.out.println(aux.paciente);

	        aux = aux.siguiente;

	    }

	}
	public Paciente buscarPaciente(String dni) {

	    Nodo aux = inicio;

	    while (aux != null) {

	        if (aux.paciente.getDni().equals(dni)) {

	            return aux.paciente;

	        }

	        aux = aux.siguiente;

	    }

	    return null;

	}
}