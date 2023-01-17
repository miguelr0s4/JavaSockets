package sockets;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ServidorSocket extends JFrame {

	public static final int PORTA = 10000;

	// o socket que fará a comunicação com o servidor
	private static Socket cliente;

	private JPanel contentPane;
	private static JTextArea telaServidor;


	private static void iniciaServidor() {
		try {
			//serviço de escuta
			ServerSocket servidor = new ServerSocket(PORTA);
			System.out.println("Servidor iniciado na porta "+PORTA+".");

			//o canal (cliente) de comunicação para esse serviço
			Socket cliente = servidor.accept();
			System.out.println("Cliente do ip "+cliente.getInetAddress().getHostAddress()+" conectado.");

			Scanner entradaDados = new Scanner(cliente.getInputStream());

			while(entradaDados.hasNextLine()) {
				String msg = entradaDados.nextLine();
				System.out.println("Mensagem recebida do cliente: "+msg);
				telaServidor.append("Mensagem recebida do cliente:"+msg+"\n\n");
			}

			entradaDados.close();
			servidor.close();

		} catch (IOException e) {
			System.out.println("Erro ao criar o servidor: "+e.getMessage());
		}
	}

	public ServidorSocket() throws Exception{

		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 242);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		telaServidor = new JTextArea();
		
		telaServidor.setBounds(44, 41, 386, 101);
		contentPane.add(telaServidor);
		telaServidor.setColumns(10);
		telaServidor.setEnabled(false);

		JLabel lblNewLabel = new JLabel("Servidor - Sa�da de dados");
		lblNewLabel.setBounds(44, 16, 165, 14);
		contentPane.add(lblNewLabel);
		
		iniciaServidor();

	}

}

