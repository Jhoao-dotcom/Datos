package Estructuras;
import modelo.Paciente;

public class ListaDoblementeEnlazada {

    private NodoDoble inicio;
    private NodoDoble fin;

    public ListaDoblementeEnlazada() {

        inicio = null;
        fin = null;
    }
    
    public void agregarAlta(Paciente paciente) {
        NodoDoble nuevo = new NodoDoble(paciente);
        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            nuevo.anterior = fin;
            fin = nuevo;
        }
    }

    public void mostrarInicioFin() {

        if (inicio == null) {
            System.out.println("No existen pacientes dados de alta.");
            return;
        }
        
        NodoDoble aux = inicio;
        while (aux != null) {
            System.out.println(aux.paciente);
            aux = aux.siguiente;
        }
    }
    
    public void mostrarFinInicio() {
        if (fin == null) {
            System.out.println("No existen pacientes dados de alta.");
            return;
        }
        NodoDoble aux = fin;
        while (aux != null) {
            System.out.println(aux.paciente);
            aux = aux.anterior;
        }
    }
}
