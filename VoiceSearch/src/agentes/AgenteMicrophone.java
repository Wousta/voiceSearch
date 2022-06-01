package agentes;

import jade.core.behaviours.*;
import other.JavaSoundRecorder;
import other.Utils;
import agentes.launcher.AgentBase;
import agentes.launcher.AgentModel;

public class AgenteMicrophone extends AgentBase {

	private static final long serialVersionUID = 1L;
	public static final String NICKNAME = "Microphone";

	protected void setup() {
		super.setup();
		this.type=AgentModel.GRABADORA;
		registerAgentDF();
		addBehaviour(new Grabadora());
	}	


	private class Grabadora extends OneShotBehaviour{
		
		private static final long serialVersionUID = 1L;

		@Override
		public void action() {
			System.out.println("Agente Grabadora a su servicio");
			recordSound();
			Utils.enviarMensaje(this.myAgent, "Recognizer", "sound.wav");
			System.out.println("Mensaje enviado desde grabadora a VoiceRecognizer"); 
		}
	}

	public void recordSound() {
		final JavaSoundRecorder recorder = new JavaSoundRecorder();
		final long RECORD_TIME = 16000;
		Thread stopper = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(RECORD_TIME);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				recorder.finish();
			}
		});
		stopper.start();
		recorder.start();
	}
}