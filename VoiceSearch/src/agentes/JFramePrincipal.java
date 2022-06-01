package agentes;

import agentes.AgenteInterfaz;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class JFramePrincipal extends JFrame {
	private JFrame frame;
	public JTextArea area;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFramePrincipal window = new JFramePrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFramePrincipal() {
	}

	public JFramePrincipal(AgenteInterfaz a) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.black);
		setBounds(100, 100, 694, 533);
		getContentPane().setLayout(null);

		JLabel etiqueta=new JLabel("Ed sheeran");//Cambiar esto a lo que reciba del anterior agente
		etiqueta.setBounds(61,58,190,55);
		etiqueta.setForeground(Color.white);
		etiqueta.setFont(new Font("Helvetica", Font.PLAIN, 30));
		add(etiqueta);

		JButton btnBuscar = new JButton("Obtener información");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Enviamos el texto que ha introducido el Cliente
				String texto = etiqueta.getText();
				a.text = texto;
				a.doWake();
			}
		});
		btnBuscar.setBounds(391, 55, 200, 50);
		getContentPane().add(btnBuscar);

		/*JTextArea textArea = new JTextArea();
		textArea.setBounds(61, 53, 190, 55);
		getContentPane().add(textArea);*/

		JLabel lblIntroduzcaTextoA = new JLabel("Se ha detectado el siguiente artista:");
		lblIntroduzcaTextoA.setBounds(61, 46, 232, 20);
		lblIntroduzcaTextoA.setForeground(Color.white);
		getContentPane().add(lblIntroduzcaTextoA);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(61, 192, 561, 238);
		getContentPane().add(textArea_1);
		area = textArea_1;

		JLabel lblResultadoBsqueda = new JLabel("Resultado de la b\u00FAsqueda:");
		lblResultadoBsqueda.setBounds(61, 155, 200, 20);
		lblResultadoBsqueda.setForeground(Color.white);
		getContentPane().add(lblResultadoBsqueda);

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
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(0, 0, 1000, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JTextArea getArea() {
		return area;
	}
}