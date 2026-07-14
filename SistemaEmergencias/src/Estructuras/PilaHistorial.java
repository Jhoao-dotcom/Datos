package Estructuras;

public class PilaHistorial {

    private NodoPila cima;

    public PilaHistorial() {

        cima = null;
    }
    
    public void apilar(String accion) {
        NodoPila nuevo = new NodoPila(accion);

        nuevo.siguiente = cima;
        cima = nuevo;
    }
    
    public void mostrarHistorial() {

        if (cima == null) {

            System.out.println("No hay historial.");

            return;

        }

        NodoPila aux = cima;

        while (aux != null) {

            System.out.println(aux.accion);

            aux = aux.siguiente;

        }

    }
    public String desapilar() {

        if (cima == null) {

            return null;

        }

        String accion = cima.accion;

        cima = cima.siguiente;

        return accion;

    }
}
