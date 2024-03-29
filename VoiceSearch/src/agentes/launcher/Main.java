package agentes.launcher;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;
import java.io.IOException;
import agentes.AgenteMicrophone;
import agentes.AgenteVoiceRecognizer;
import agentes.JFramePrincipal;
import agentes.AgenteInterfaz;


public class Main {

	private static jade.wrapper.AgentContainer cc;
	
    private static void loadBoot() throws ControllerException{
        jade.core.Runtime rt = jade.core.Runtime.instance();
        rt.setCloseVM(true);
        Profile profile = new ProfileImpl(null, 1200, null);
        try {
        cc = rt.createMainContainer(profile);
            ProfileImpl pContainer = new ProfileImpl(null, 1200, null);
            rt.createAgentContainer(pContainer);
            cc.createNewAgent("rma","jade.tools.rma.rma", new Object[0]).start();
            cc.createNewAgent(AgenteMicrophone.NICKNAME, AgenteMicrophone.class.getName(), new Object[]{"0"}).start();
            cc.createNewAgent(AgenteVoiceRecognizer.NICKNAME, AgenteVoiceRecognizer.class.getName(), new Object[]{"0"}).start();
            cc.createNewAgent(AgenteInterfaz.NICKNAME, AgenteInterfaz.class.getName(), new Object[]{"0"}).start();
            System.out.println("Agentes y contenedor creados");
        } catch (StaleProxyException e) {
            System.err.println("Error en el inicio de los agentes, cierra la pesta�a abierta");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public static jade.wrapper.AgentContainer getCc() {
		return cc;
	}

	public static void main(String[] args) throws IOException, ControllerException {
        System.out.println("Comenzando...");
        loadBoot();
    }
}
