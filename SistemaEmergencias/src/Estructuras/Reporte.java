package Estructuras;

public class Reporte {

	private int reporte[][];
	public Reporte() {
	reporte = new int[3][2];
	}
	private String niveles[] = {
		    "Leve",
		    "Moderado",
		    "Grave"
		};
	   
	public void registrarPaciente(String gravedad, String sexo) {

	    int fila = 0;
	    int columna = 0;

	    if (gravedad.equalsIgnoreCase("Leve")) {

	        fila = 0;

	    } else if (gravedad.equalsIgnoreCase("Moderado")) {

	        fila = 1;

	    } else {

	        fila = 2;

	    }

	    if (sexo.equalsIgnoreCase("Masculino")) {

	        columna = 0;

	    } else {

	        columna = 1;

	    }

	    reporte[fila][columna]++;

	}
	
	public void mostrarReporte() {

	    System.out.println("\n===== REPORTE ESTADÍSTICO =====");
	    System.out.println("\tMasculino\tFemenino");
	    System.out.println("Leve\t\t" + reporte[0][0] + "\t\t" + reporte[0][1]);
	    System.out.println("Moderado\t" + reporte[1][0] + "\t\t" + reporte[1][1]);
	    System.out.println("Grave\t\t" + reporte[2][0] + "\t\t" + reporte[2][1]);

	    System.out.println(niveles[0]);
	    System.out.println(niveles[1]);
	    System.out.println(niveles[2]);
	}
}


