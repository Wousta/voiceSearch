package agentes;

import javax.swing.JFrame;
import agentes.JFramePrincipal;
import agentes.AgenteInterfaz;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.io.IOException;
import java.awt.Dimension;

public class MainGUI extends Thread {
	String titulo;
	AgenteInterfaz agenteInterfaz;
	String artist;
	String info;
	JFramePrincipal frame;

	public MainGUI(String tit, AgenteInterfaz a) {
		this.titulo = "Agente Interfaz: " + tit;
		this.agenteInterfaz = a;
	}

	public void run() {
		//El interfaz se basar� en un contendor de tipo Jframe, Lo personalizamos para que pueda gestionar la informaci�n del hilo
		JFrame jFrame;
		jFrame = new JFramePrincipal(agenteInterfaz, null);
		frame = (JFramePrincipal) jFrame;
		jFrame.setTitle("Voice Search");
		jFrame.setResizable(false);	
		jFrame.setVisible(true);
		jFrame.setLocationRelativeTo(null);
	}

	public JFramePrincipal getFrame() {
		return frame;
	}
}

