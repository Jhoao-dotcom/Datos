package Estructuras;
import modelo.Paciente;


public class NodoCola {

	    Paciente paciente;
	    NodoCola siguiente;

	    public NodoCola(Paciente paciente) {

	        this.paciente = paciente;
	        this.siguiente = null;

	    }
}
