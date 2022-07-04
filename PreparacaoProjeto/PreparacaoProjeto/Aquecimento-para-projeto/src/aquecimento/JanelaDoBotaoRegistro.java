package aquecimento;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.itextpdf.text.Font;

import aquecimento.JanelaDeLogin.OuvinteDoBotaoRegistrar;

public class JanelaDoBotaoRegistro extends JFrame {
	Persistencia p = new Persistencia();
	CentralDeInformacoes c = p.recuperar();
	Usuario u = new Usuario();
	
private	JTextField login;
private JTextField senha;

	public JTextField getLogin() {
	return login;
	}

	public JTextField getSenha() {
	return senha;
	}



	public void janelaDeRegistro(String[]  pLista, String tipo ) {
		// Gera a tela
		setTitle("Janela De Registro");
		setSize(400,300);
		setLocation(500,300);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	  
	  
		
		//Icones Da tela 
		textolField(this);
		// Texto para escrever 
		adicionarLabel(this);
		//adiciona titulo
		entrada();
		// Referencia a entrada de login e senha.
		botao();
 }
	
	public class OuvinteDosBotoes implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String botaoPresionado = e.getActionCommand();
			
			switch (botaoPresionado) {
			case "Voltar":
				dispose();
				new JanelaDeLogin().JanelaGerador();
				break;
			
			case "Cadastrar":
				String loginDoUsuario = login.getText(); String senhaDoUsuario = senha.getText();
				
				Usuario novo = new Usuario();
				u.setLogin(loginDoUsuario);
				u.setSenha(senhaDoUsuario);
				boolean validadorJ = false;
				boolean validadorS = false;
				try {
					u.validarLogin(u);
					validadorJ = true;
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(rootPane, e2.getMessage());
					
				}
				
				try {
					u.validarSenha(u);
					validadorS = true;
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(rootPane, e2.getMessage());
					
				}
			if(validadorJ && validadorS) {	
				try {
				c.adicionarUsuario(u);
				p.salvar(c);
				JOptionPane.showMessageDialog(rootPane,"Seja Bem vindo: " + loginDoUsuario);					
				dispose();
				
				} catch (Exception e1) {		
					JOptionPane.showMessageDialog(rootPane, e1.getMessage());
				}
				
			}	 
			}
				 
			}
		}
		
		
	
 
  public void textolField(JFrame frame) {
	  	login = new JTextField("");
		login.setBounds(100,75,200,30);
		login.addKeyListener(null);
		frame.add(login);
	
		senha = new JTextField("");
		senha.setBounds(100,120,200,30);
		senha.addKeyListener(null);
		frame.add(senha);	
		
	  
  }
  
 public void adicionarLabel(JFrame frame) {
	 ImageIcon iconeT = new ImageIcon("Login2.png");
		JLabel texto = new JLabel(" Tela de Registro",iconeT,JLabel.LEFT);
		
		texto.setBounds(130,20,200,20);
		texto.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
		texto.setForeground(Color.BLACK);
		
		frame.add(texto);
	 
	 
 }
	public void entrada() {
		
		JLabel login = new JLabel("Nome: ");
		login.setBounds(30, 75, 100, 30);
		add(login);
		
		JLabel senha = new JLabel("Senha: ");
		senha.setBounds(30,120,100,30);
		add(senha);
	}
 
	public void botao() {
		OuvinteDosBotoes ouvindoBotoes = new OuvinteDosBotoes();
		
		JButton voltar = new JButton("Voltar");
		voltar.setBounds(30,200,100 ,30 );
		voltar.addActionListener(ouvindoBotoes);
		add(voltar);
		
		JButton registrar = new JButton("Cadastrar");
		registrar.setBounds(260,200,100,30);
		registrar.addActionListener(ouvindoBotoes);
		add(registrar);
		
	}
 
	}

