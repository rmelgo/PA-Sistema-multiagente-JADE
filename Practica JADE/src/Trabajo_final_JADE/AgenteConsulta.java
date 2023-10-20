package Trabajo_final_JADE;

import java.util.Scanner;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class AgenteConsulta extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected OneShotBehaviourConsulta OneShotBehaviourAgenteConsulta = null;
	
	public void setup() {
		Utils.registrarServicio(this, "Consulta", "consultar");
		
		System.out.println("=============================================================");
		System.out.println("Bienvenido al servicio de consultas del hospital de Salamanca");
		System.out.println("=============================================================");
		OneShotBehaviourAgenteConsulta = new OneShotBehaviourConsulta(this);
		addBehaviour(OneShotBehaviourAgenteConsulta);
	}
}

class OneShotBehaviourConsulta extends OneShotBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OneShotBehaviourConsulta (Agent ownerAgent) {  
	    super(ownerAgent);   
	}

	public void action() {
		System.out.println("\nLos servicios disponibles son: Cardiologia, Neurologia, Urgencias, Traumatologia, Neumologia, Aparato digestivo, Urologia y Atencion primaria");
		System.out.println("\nIntroduce el tipo de medico con el cual desea concentar una cita: ");
		Scanner sc = new Scanner(System.in);
		String tipo_medico = sc.nextLine();
		
		//comprobar que el tipo de medico introducido es valido
			
		Utils.enviarMensaje(this.myAgent, "buscar", tipo_medico);
		
		ACLMessage msg= this.myAgent.blockingReceive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST), MessageTemplate.MatchOntology("ontologia")));
		try
		{
			String[] informacion_consulta = (String [])msg.getContentObject();
			if (informacion_consulta != null) {
		        System.out.println("\nCita concertada con el doctor " + informacion_consulta[0] + " " + informacion_consulta[1] + " en la fecha " + informacion_consulta[2]);
			}
			else {
				System.out.println("\nNo existen medicos de la especialidad " + tipo_medico);
			}
		}
		catch (UnreadableException e)
		{
		    e.printStackTrace();
		}						
	}		
}