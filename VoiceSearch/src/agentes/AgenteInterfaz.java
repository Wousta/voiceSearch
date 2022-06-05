package agentes;

import jade.core.Agent;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import agentes.launcher.AgentBase;
import agentes.launcher.AgentModel;
import other.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;


public class AgenteInterfaz extends AgentBase {
	public String text;

	private static final long serialVersionUID = 1L;
	public static final String NICKNAME = "Interfaz";

	public void setup() {

		super.setup();
		this.type=AgentModel.INTERFAZ;
		registerAgentDF();

		addBehaviour(new CyclicBehaviour(this) {
			private static final long serialVersionUID = 1L;

			public void action() {
				ACLMessage msg = this.myAgent.blockingReceive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
						MessageTemplate.MatchOntology("ontologia")));
				System.out.println("Agente interfaz a su servicio");
				//System.out.println("msg info:" + msg.getContent());
				//myAgent.doWait();
				// temp);
				try {
					@SuppressWarnings("unchecked")
					List<String> mensajes = (List<String>) msg.getContentObject();
					System.out.println(mensajes);
					//					gui.getFrame().getArea().setText("");
					Utils.enviarMensaje(this.myAgent, "buscar", text);

					JFramePrincipal window = new JFramePrincipal(this.myAgent, mensajes.toArray());
					window.initialize();
					//window.getFrame().setVisible(true);
					window.setTitle("Voice Search");
					window.setResizable(false);	
					window.setVisible(true);
					window.setLocationRelativeTo(null);				
				} catch (UnreadableException e) {
					e.printStackTrace();
				}
			}
		});
	}
}