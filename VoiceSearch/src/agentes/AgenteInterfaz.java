package agentes;

import jade.core.Agent;
import java.util.List;
import other.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;


public class AgenteInterfaz extends Agent {
	public String text;
	String temp;

	private static final long serialVersionUID = 1L;
	public static final String NICKNAME = "Interfaz";

	public void setup() {
		
		
		MainGUI gui = new MainGUI(this.getLocalName(), this);
		gui.run();
		addBehaviour(new CyclicBehaviour(this) {
			private static final long serialVersionUID = 1L;

			public void action() {
				ACLMessage msg = blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
				System.out.println("Agente interfaz a su servicio");
				//myAgent.doWait();
				gui.getFrame().getArea().setText("");
				Utils.enviarMensaje(this.myAgent, "buscar", text);// temp);
				try {
					List<String> mensajes = (List<String>) msg.getContentObject();
					System.out.println(mensajes);
					for (int i = 0; i < mensajes.size(); i++) {
						/*System.out.println(mensajes.get(i));
						temp = gui.getFrame().getArea().getText();
						gui.getFrame().getArea().setText(temp + "\n" + mensajes.get(i));*/
					}
					temp = "";
				} catch (UnreadableException e) {
					e.printStackTrace();
				}
			}
		});
	}
}