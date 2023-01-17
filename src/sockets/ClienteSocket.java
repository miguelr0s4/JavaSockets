package sockets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ClienteSocket extends JFrame {
	
	public static final int PORTA = 10000;
	
	// socket que fará a comunicação com o servidor
	private static Socket cliente;
	
	private JPanel contentPane;
	private JTextField mensagem;
	private static JTextArea telaServidor;


	public ClienteSocket()  throws Exception{

		
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 242);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mensagem = new JTextField();
		mensagem.setBounds(44, 156, 227, 20);
		contentPane.add(mensagem);
		mensagem.setColumns(10);
		
		JButton enviarMensagem = new JButton("Enviar Mensagem");
		
		contentPane.add(enviarMensagem);
		
		enviarMensagem.setBounds(281, 155, 149, 23);
		enviarMensagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = mensagem.getText();
				enviarMensagem(msg);
				mensagem.setText("");
				telaServidor.append("Mensagem recebida do cliente:"+msg+"\n\n");

			}
		});
		
		telaServidor = new JTextArea();
		telaServidor.setBounds(44, 25, 386, 101);
		contentPane.add(telaServidor);
		telaServidor.setColumns(10);
		telaServidor.setEnabled(false);
		
		iniciaCliente();		
	}


	private void iniciaCliente() {
		String ipserver;
		try {
			cliente = new Socket("127.0.0.1", PORTA);
		} catch (IOException e) {
			System.out.println("Erro de conexão ao servidor: "+e.getMessage());
		}
	}
	
	private void enviarMensagem(String msg) {
		//canal de saída 
		try {
			PrintStream saidaDados = new PrintStream(cliente.getOutputStream());
			saidaDados.println(msg);
			
			System.out.println("Mensagem enviada para o servidor: "+msg);
			
		} catch (IOException e) {
			System.out.println("Erro ao enviar mensagem."+e.getMessage());
		}

	}
}
