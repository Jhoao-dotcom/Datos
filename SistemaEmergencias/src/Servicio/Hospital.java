package Servicio;

import java.util.Scanner;

import Estructuras.ArbolABB;
import Estructuras.ColaPacientes;
import Estructuras.ListaDoblementeEnlazada;
import Estructuras.ListaPacientes;
import Estructuras.Reporte;
import modelo.Paciente;

public class Hospital {

    private ListaPacientes lista;
    private ColaPacientes cola;
    private ArbolABB arbol;
    private ListaDoblementeEnlazada altas;
    private Reporte reporte;

    public Hospital() {

        lista = new ListaPacientes();
        cola = new ColaPacientes();
        arbol = new ArbolABB();
        altas = new ListaDoblementeEnlazada();
        reporte = new Reporte();

    }

    // Registrar paciente
    public void registrarPaciente(Scanner sc) {

        System.out.print("DNI: ");
        String dni = sc.nextLine();

        if (arbol.buscar(dni) != null) {
            System.out.println("El paciente ya existe.");
            return;
        }

        System.out.print("Nombres: ");
        String nombres = sc.nextLine();

        System.out.print("Edad: ");
        int edad = Integer.parseInt(sc.nextLine());

        System.out.print("Sexo: ");
        String sexo = sc.nextLine();

        System.out.print("Gravedad (Leve/Moderado/Grave): ");
        String gravedad = sc.nextLine();

        System.out.print("Fecha de ingreso: ");
        String fecha = sc.nextLine();

        Paciente paciente = new Paciente(
                dni,
                nombres,
                edad,
                sexo,
                gravedad,
                fecha,
                "Registrado");

        lista.agregarPaciente(paciente);
        arbol.insertar(paciente);
        reporte.registrarPaciente(gravedad, sexo);

        paciente.getHistorial().apilar("Paciente registrado");

        System.out.println("Paciente registrado correctamente.");

    }

    // Mostrar pacientes
    public void mostrarPacientes() {
        lista.mostrarPacientes();
    }

    // Buscar paciente
    public void buscarPaciente(Scanner sc) {

        System.out.print("Ingrese DNI: ");
        String dni = sc.nextLine();

        Paciente paciente = arbol.buscar(dni);

        if (paciente != null) {

            System.out.println(paciente);

        } else {

            System.out.println("Paciente no encontrado.");

        }

    }

    // Enviar a cola
    public void enviarCola(Scanner sc) {

        System.out.print("Ingrese DNI del paciente: ");
        String dni = sc.nextLine();

        Paciente paciente = arbol.buscar(dni);

        if (paciente == null) {

            System.out.println("Paciente no encontrado.");
            return;

        }

        if (paciente.getEstado().equalsIgnoreCase("En espera")) {

            System.out.println("El paciente ya está en la cola.");
            return;

        }

        cola.encolar(paciente);
        paciente.setEstado("En espera");
        paciente.getHistorial().apilar("Enviado a cola");

        System.out.println("Paciente enviado a la cola.");

    }

    // Mostrar cola
    public void mostrarCola() {
        cola.mostrarCola();
    }

    // Atender paciente
    public void atenderPaciente() {

        Paciente paciente = cola.desencolar();

        if (paciente == null) {

            System.out.println("No hay pacientes en espera.");
            return;

        }

        paciente.setEstado("Atendido");
        paciente.getHistorial().apilar("Paciente atendido");

        System.out.println("Paciente atendido:");
        System.out.println(paciente);

    }

    // Dar alta
    public void darAlta(Scanner sc) {

        System.out.print("Ingrese DNI: ");
        String dni = sc.nextLine();

        Paciente paciente = arbol.buscar(dni);

        if (paciente == null) {

            System.out.println("Paciente no encontrado.");
            return;

        }

        paciente.setEstado("Alta");
        paciente.getHistorial().apilar("Paciente dado de alta");

        altas.agregarAlta(paciente);

        System.out.println("Paciente dado de alta.");

    }

    // Mostrar altas
    public void mostrarAltas() {
        altas.mostrarInicioFin();
    }

    // Mostrar altas inverso
    public void mostrarAltasInverso() {
        altas.mostrarFinInicio();
    }

    // Reporte
    public void mostrarReporte() {
        reporte.mostrarReporte();
    }

    // Historial
    public void mostrarHistorial(Scanner sc) {

        System.out.print("Ingrese DNI: ");
        String dni = sc.nextLine();

        Paciente paciente = arbol.buscar(dni);

        if (paciente == null) {

            System.out.println("Paciente no encontrado.");
            return;

        }

        paciente.getHistorial().mostrarHistorial();

    }

}