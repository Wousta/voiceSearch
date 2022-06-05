package agentes;

import other.IdentifyProtocolV1;
import other.Utils;
import agentes.launcher.AgentModel;
import instantAnswerAPI.DuckDuckGo;
import instantAnswerAPI.Response;
import agentes.launcher.AgentBase;
import jade.content.lang.sl.SLCodec;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.domain.FIPAAgentManagement.Envelope;
import jade.lang.acl.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class AgenteVoiceRecognizer extends AgentBase {


	private static final long serialVersionUID = 1L;
	public static final String NICKNAME = "Recognizer";

	protected void setup()
	{
		super.setup();
		this.type=AgentModel.RECOGNIZER;
		registerAgentDF();
		addBehaviour(new Reconoce());
	}

	private class Reconoce extends OneShotBehaviour{

		private static final long serialVersionUID = 1L;

		@Override
		public void action() {
			ACLMessage msg = this.myAgent.blockingReceive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
					MessageTemplate.MatchOntology("ontologia")));
			System.out.println("Agente VoiceRecognizer");
			System.out.println("Mensaje: " + (String) msg.getContent());

			/******************  RECONOCIMIENTO  ********************/

			String[] artistas;
			try {
				String recog = IdentifyProtocolV1.main(msg.getContent());

				JSONObject json = new JSONObject(recog);
				JSONArray artists = json.getJSONObject("metadata").getJSONArray("music").getJSONObject(0).getJSONObject("external_metadata")
						.getJSONObject("spotify").getJSONArray("artists");
				artistas = new String[artists.length()];
				for(int i = 0; i<artists.length();i++) {
					artistas[i] = artists.getJSONObject(i).get("name").toString();
				}

				/******************  BÚSQUEDA  ********************/

				DuckDuckGo ddg = new DuckDuckGo();
				Response r = null;
				String author = artistas[0];
				r = ddg.query(author);

				String abstrac = r.getAbstract().getAbstractText();
				List<String> result = new ArrayList<String>();
				result.add(author);
				result.add(abstrac);

				/******************  ENVÍO  ********************/


				//Utils.enviarMensaje(this.myAgent, "Interfaz", res);
				ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);				
				Utils.enviarMensaje(this.myAgent, "Interfaz", result);
				System.out.println("Información de " + author + " enviada a la interfaz");
			} catch (Exception e) {
				System.out.println("cancion no encontrada, intentalo de nuevo");
			}
		}
	}
}
