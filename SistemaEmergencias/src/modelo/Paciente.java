package modelo;

import Estructuras.PilaHistorial;

public class Paciente {

	   private String dni;
	   private String nombres;
	   private int edad;
	   private String sexo;
	   private String gravedad;
	   private String fechaIngreso;
	   private String estado;
	   private PilaHistorial historial;
	    // Constructor
	   public Paciente(String dni, String nombres, int edad, String sexo,
	            String gravedad, String fechaIngreso, String estado) {

	     this.dni = dni;
	     this.nombres = nombres;
         this.edad = edad;
	     this.sexo = sexo;
	     this.gravedad = gravedad;
         this.fechaIngreso = fechaIngreso;
	     this.estado = estado;
	     historial = new PilaHistorial();
	    }

	    // Getters
	    public String getDni() {
	        return dni;
	    }

	    public String getNombres() {
	        return nombres;
	    }

	    public int getEdad() {
	        return edad;
	    }

	    public String getSexo() {
	        return sexo;
	    }

	    public String getGravedad() {
	        return gravedad;
	    }

	    public String getFechaIngreso() {
	        return fechaIngreso;
	    }

	    public String getEstado() {
	        return estado;
	    }

	    // Setter
	    public void setEstado(String estado) {
	        this.estado = estado;
	    }

	    @Override
	    public String toString() {

	        return "DNI: " + dni +
	               "\nNombre: " + nombres +
	               "\nEdad: " + edad +
	               "\nSexo: " + sexo +
	               "\nGravedad: " + gravedad +
	               "\nFecha Ingreso: " + fechaIngreso +
	               "\nEstado: " + estado +
	               "\n----------------------------";
	    }

		public PilaHistorial getHistorial() {
			return historial;
		}

		public void setHistorial(PilaHistorial historial) {
			this.historial = historial;
		}
}
