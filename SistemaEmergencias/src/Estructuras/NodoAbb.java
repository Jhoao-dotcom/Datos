package Estructuras;

import modelo.Paciente;

public class NodoAbb {

    Paciente paciente;
    NodoAbb izquierda;
    NodoAbb derecha;

    public NodoAbb(Paciente paciente) {

        this.paciente = paciente;
        this.izquierda = null;
        this.derecha = null;

    }
}
