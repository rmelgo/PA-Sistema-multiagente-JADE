package Trabajo_final_JADE;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Doctor {

	private String nombre;
	private String apellido;
	private String especialidad;
	List<String> fechas;
	List<Integer> consultas_disponibles;
	
	public Doctor() {
		this.nombre = null;
		this.apellido = null;
		this.especialidad = null;
		this.fechas = new ArrayList<>();
		this.consultas_disponibles = new ArrayList<>();
	}
	
	public Doctor(String nombre, String apellido, String especialidad, Date fecha_inicial, Date fecha_final) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.especialidad = especialidad;
		this.fechas = obtenerListaFechasEntreDosFechas(fecha_inicial, fecha_final);
		this.consultas_disponibles = new ArrayList<>();
		for (int i = 0; i < this.fechas.size(); i++) {
			Random claseRandom = new Random(); 
			this.consultas_disponibles.add(claseRandom.nextInt(10));
		}
	}

    public static List<String> obtenerListaFechasEntreDosFechas(Date start, Date end) {
        List<String> result = new ArrayList<String>();
 
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
 
        tempStart.add(Calendar.DAY_OF_YEAR, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        result.add(sdf.format(start));
        while (tempStart.before(tempEnd)) {
            result.add(sdf.format(tempStart.getTime()));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }
    
    public String getNombre() {
    	return this.nombre;
    }
    
    public String getApellido() {
    	return this.apellido;
    }
    
    public String getEspecialidad() {
    	return this.especialidad;
    }
    
    public List<String> getFechas() {
    	return this.fechas;
    }
    
    public List<Integer> getConsultasDisponibles() {
    	return this.consultas_disponibles;
    }
    
    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }
    
    public void setApellido(String apellido) {
    	this.apellido = apellido;
    }
    
    public void setEspecialidad(String especialidad) {
    	this.especialidad = especialidad;
    }
    
    public void setFechas(List<String> fechas) {
    	this.fechas = fechas;
    }
    
    public void setConsultasDisponibles(List<Integer> consultas_disponibles) {
    	this.consultas_disponibles = consultas_disponibles;
    }
}
