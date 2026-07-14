package Estructuras;

import modelo.Paciente;

public class ArbolABB {

    private NodoAbb raiz;

    public ArbolABB() {

        raiz = null;
    }
    
    public void insertar(Paciente paciente) {

        raiz = insertarRecursivo(raiz, paciente);
    }
    
    private NodoAbb insertarRecursivo(NodoAbb actual, Paciente paciente) {

        if (actual == null) {
            return new NodoAbb(paciente);
        }

        int dniNuevo = Integer.parseInt(paciente.getDni());
        int dniActual = Integer.parseInt(actual.paciente.getDni());
        if (dniNuevo < dniActual) {

            actual.izquierda = insertarRecursivo(actual.izquierda, paciente);

        } else if (dniNuevo > dniActual) {

            actual.derecha = insertarRecursivo(actual.derecha, paciente);
        }

        return actual;
    }
    
    public Paciente buscar(String dni) {

        return buscarRecursivo(raiz, Integer.parseInt(dni));
    }

    private Paciente buscarRecursivo(NodoAbb actual, int dni) {

        if (actual == null) {

            return null;

        }

        int dniActual = Integer.parseInt(actual.paciente.getDni());

        if (dni == dniActual) {

            return actual.paciente;

        }

        if (dni < dniActual) {

            return buscarRecursivo(actual.izquierda, dni);

        }

        return buscarRecursivo(actual.derecha, dni);

    }
    
    
}
