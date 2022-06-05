package agentes;

import agentes.AgenteInterfaz;
import agentes.launcher.Main;
import jade.core.Agent;
import jade.wrapper.ControllerException;
import other.Utils;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class JFramePrincipal extends JFrame {
	private JFrame frame;
	private JTextArea area;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JFramePrincipal window = new JFramePrincipal();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public JFramePrincipal() {
	}

	public JFramePrincipal(Agent myAgent, Object[] objects) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.black);
		setBounds(100, 100, 694, 533);
		getContentPane().setLayout(null);

		//
		JLabel etiqueta=new JLabel((String) objects[0]);//Cambiar esto a lo que reciba del anterior agente
		etiqueta.setBounds(61,58,190,55);
		etiqueta.setForeground(Color.white);
		etiqueta.setFont(new Font("Helvetica", Font.PLAIN, 30));
		add(etiqueta);

		JButton btnBuscar = new JButton("Buscar otra cancion");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				WindowEvent closingEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
//				Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
				frame.setVisible(false);
				try {
					Main.getCc().getAgent(AgenteMicrophone.NICKNAME).activate();
				} catch (ControllerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnBuscar.setBounds(391, 55, 200, 50);
		getContentPane().add(btnBuscar);

		/*JTextArea textArea = new JTextArea();
		textArea.setBounds(61, 53, 190, 55);
		getContentPane().add(textArea);*/
		JLabel lblResultadoBsqueda = new JLabel("Resultado de la b\u00FAsqueda:");
		lblResultadoBsqueda.setBounds(61, 155, 200, 20);
		lblResultadoBsqueda.setForeground(Color.white);
		getContentPane().add(lblResultadoBsqueda);

		
		JLabel lblIntroduzcaTextoA = new JLabel("Se ha detectado el siguiente artista:");
		lblIntroduzcaTextoA.setBounds(61, 46, 532, 20);
		lblIntroduzcaTextoA.setForeground(Color.white);
		getContentPane().add(lblIntroduzcaTextoA);

		JTextArea textArea_1 = new JTextArea((String) objects[1]);
		textArea_1.setBounds(61, 192, 561, 238);
		textArea_1.setText((String) objects[1]);
		textArea_1.setLineWrap(true);
        textArea_1.setWrapStyleWord(true);
		getContentPane().add(textArea_1);
		area = textArea_1;
		myAgent.doWake();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {

		frame = new JFrame();
		frame.setBounds(0, 0, 1000, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JTextArea getArea() {
		return area;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void setArea(JTextArea area) {
		this.area = area;
	}
}