package Estructuras;
import modelo.Paciente;


public class Nodo {

	    Paciente paciente;
	    Nodo siguiente;

	    public Nodo(Paciente paciente) {
	        this.paciente = paciente;
	        this.siguiente = null;
	    }
}
