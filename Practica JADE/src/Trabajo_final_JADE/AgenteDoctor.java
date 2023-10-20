package Trabajo_final_JADE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class AgenteDoctor extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected CyclicBehaviourDoctor CyclicBehaviourAgenteDoctor = null;
		
	public void setup() {
		Utils.registrarServicio(this, "Doctor", "doctor");
		
		CyclicBehaviourAgenteDoctor = new CyclicBehaviourDoctor(this);
		addBehaviour(CyclicBehaviourAgenteDoctor);
	}

}

class CyclicBehaviourDoctor extends CyclicBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Doctor> listaDoctores = new ArrayList<>();
	
	public CyclicBehaviourDoctor (Agent ownerAgent) {  
	    super(ownerAgent);   
	}

	public void action() {
		//creacion de doctores
		Date fecha_inicial = new Date(121, 11, 13);
		Date fecha_final = new Date(121, 11, 31);
		
		Doctor doc;
		
		doc = new Doctor("Pedro Luis", "Sanchez", "Cardiologia", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		doc = new Doctor("Francisco", "Martin", "Cardiologia", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		doc = new Doctor("Jose Luis", "Moriñigo", "Cardiologia", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		doc = new Doctor("Javier", "Rodriguez", "Cardiologia", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		
		doc = new Doctor("Jose Maria", "Gutierrez", "Neurologia", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		doc = new Doctor("Yasmina", "Berdei", "Neurologia", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		doc = new Doctor("Jose Carlos", "Moran", "Neurologia", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		
		doc = new Doctor("Rafael", "Borras", "Urgencias", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		doc = new Doctor("Juan Jose", "Salvador", "Urgencias", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		doc = new Doctor("Miguel Angel", "Riesco", "Urgencias", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		doc = new Doctor("Teresa", "Lorenzo", "Urgencias", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		doc = new Doctor("Cristina Ana", "Gil", "Urgencias", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		
		doc = new Doctor("Juan Francisco", "Blanco", "Traumatologia", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		doc = new Doctor("David", "Pescador", "Traumatologia", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		doc = new Doctor("Javier", "Dominguez", "Traumatologia", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		
		doc = new Doctor("Miguel", "Barrueco", "Neumologia", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		doc = new Doctor("Teresa", "Antolin", "Neumologia", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		doc = new Doctor("Sergio", "Cadenas", "Neumologia", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		doc = new Doctor("Rosa", "Cordovilla", "Neumologia", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		doc = new Doctor("Jose Luis", "Fernandez", "Neumologia", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		
		doc = new Doctor("Fernando", "Geijo", "Aparato digestivo", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		doc = new Doctor("Antonio", "Velasco", "Aparato digestivo", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		
		doc = new Doctor("Francisco", "Gomez", "Urologia", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		doc = new Doctor("Fernanda", "Lorenzo", "Urologia", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		
		doc = new Doctor("Alicia", "Garcia", "Atencion primaria", fecha_inicial, fecha_final);
		this.listaDoctores.add(doc);
		
		//espero a que llegen peticiones de consulta
		
		ACLMessage msg= this.myAgent.blockingReceive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST), MessageTemplate.MatchOntology("ontologia")));
		try
		{
		    System.out.println("Buscando "+ (String)msg.getContentObject());
		}
		catch (UnreadableException e)
		{
		    e.printStackTrace();
		}
		
		//busco doctores
		
		String [] informacion_consulta = new String[3];
		int numero_dias = Doctor.obtenerListaFechasEntreDosFechas(fecha_inicial, fecha_final).size();
		int i, numero_consultas;
		boolean encontrado = false;
		
		for (i = 0; i < numero_dias; i++) {	//buscamos desde el dia mas proximo	
			for (int j = 0; j < this.listaDoctores.size(); j++) {
				doc = this.listaDoctores.get(j);
				
				try {
					if (doc.getEspecialidad().equals((String)msg.getContentObject())) {
						if (doc.getConsultasDisponibles().get(i) > 0) {
							numero_consultas =  doc.getConsultasDisponibles().get(i) - 1;
							doc.consultas_disponibles.set(i, numero_consultas);
							
							informacion_consulta[0] = doc.getNombre();
							informacion_consulta[1] = doc.getApellido();
							informacion_consulta[2] = doc.getFechas().get(i);
							
							encontrado = true;
							break;
						}
					}
				} catch (UnreadableException e) {
					e.printStackTrace();
				}
			}	
			if (encontrado == true) {
				break;
			}
		}
		
		//envio un mensaje con los resultados
		if (encontrado == true) {
		    Utils.enviarMensaje(this.myAgent, "buscar", informacion_consulta);	
		}
		else {
			Utils.enviarMensaje(this.myAgent, "buscar", null);
		}		
	}
	
}