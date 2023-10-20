package Trabajo_final_JADE;

import java.io.IOException;
import java.io.Serializable;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPAAgentManagement.Envelope;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class AgenteBuscador extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected CyclicBehaviourBuscador CyclicBehaviourAgenteBuscador = null;

	public void setup() {
		Utils.registrarServicio(this, "Buscador", "buscar");
		
		CyclicBehaviourAgenteBuscador = new CyclicBehaviourBuscador(this);
		addBehaviour(CyclicBehaviourAgenteBuscador);
	}
}

class CyclicBehaviourBuscador extends CyclicBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CyclicBehaviourBuscador (Agent ownerAgent) {  
	    super(ownerAgent);
	}

	public void action() {
		ACLMessage msg= this.myAgent.blockingReceive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST), MessageTemplate.MatchOntology("ontologia")));
		try
		{
		    System.out.println("Buscando "+ (String)msg.getContentObject());
		    Utils.enviarMensaje(this.myAgent, "doctor", (String)msg.getContentObject());
		}
		catch (UnreadableException e)
		{
		    e.printStackTrace();
		}
		
		ACLMessage msg2= this.myAgent.blockingReceive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST), MessageTemplate.MatchOntology("ontologia")));	
		    	
		//envio el mensaje solo al agente Consulta que me ha pedido la informacion
		
		ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);  	
    	
    	aclMessage.addReceiver(msg.getSender());
    	
        aclMessage.setOntology("ontologia");
        aclMessage.setLanguage(new SLCodec().getName());
        aclMessage.setEnvelope(new Envelope());
		aclMessage.getEnvelope().setPayloadEncoding("ISO8859_1"); 
		try {
			aclMessage.setContentObject((Serializable) (String [])msg2.getContentObject());
		} catch (IOException | UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.myAgent.send(aclMessage);
		
	}
	
}