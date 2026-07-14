package Principal;

import java.util.Scanner;

import Servicio.Hospital;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Hospital hospital = new Hospital();

        int opcion;

        do {

            System.out.println("\n===========================================");
            System.out.println("     SISTEMA DE EMERGENCIAS HOSPITALARIAS");
            System.out.println("===========================================");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Mostrar pacientes");
            System.out.println("3. Buscar paciente");
            System.out.println("4. Enviar paciente a cola");
            System.out.println("5. Mostrar cola");
            System.out.println("6. Atender paciente");
            System.out.println("7. Dar de alta");
            System.out.println("8. Mostrar pacientes dados de alta");
            System.out.println("9. Mostrar altas (orden inverso)");
            System.out.println("10. Mostrar reporte");
            System.out.println("11. Mostrar historial de un paciente");
            System.out.println("12. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {

            case 1:
                hospital.registrarPaciente(sc);
                break;

            case 2:
                hospital.mostrarPacientes();
                break;

            case 3:
                hospital.buscarPaciente(sc);
                break;

            case 4:
                hospital.enviarCola(sc);
                break;

            case 5:
                hospital.mostrarCola();
                break;

            case 6:
                hospital.atenderPaciente();
                break;

            case 7:
                hospital.darAlta(sc);
                break;

            case 8:
                hospital.mostrarAltas();
                break;

            case 9:
                hospital.mostrarAltasInverso();
                break;

            case 10:
                hospital.mostrarReporte();
                break;

            case 11:
                hospital.mostrarHistorial(sc);
                break;

            case 12:
                System.out.println("\n==================================");
                System.out.println(" Gracias por utilizar el sistema.");
                System.out.println(" Cerrando programa...");
                System.out.println("==================================");
                break;

            default:
                System.out.println("Opción inválida.");

            }

        } while (opcion != 12);

        sc.close();

    }

}