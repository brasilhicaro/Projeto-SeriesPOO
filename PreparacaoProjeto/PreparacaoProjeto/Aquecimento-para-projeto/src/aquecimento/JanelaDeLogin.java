package aquecimento;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.itextpdf.text.Font;

public class JanelaDeLogin extends JFrame {

	Persistencia p = new Persistencia();
	CentralDeInformacoes c = p.recuperar();
	Usuario u = new Usuario();

	private JTextField login;
	private JTextField senha;

	public JTextField getlogin() {
		return login;
	}

	public JTextField getSenha() {
		return senha;
	}

	public void JanelaGerador() {
		janelaDeLogin();

	}

	private void janelaDeLogin() {
		// Gera a tela
		setTitle("Tela De Login");
		setSize(400, 300);
		setLocation(500, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// Icones Da tela

		botao();
		// bot�es
		textolfield(this);
		// Textos Para escrever
		adicionarLabel(this);
		// Titulo da tela
		entrada();
		// Referencia a entrada s� e login ou se e senha.

		repaint();

	}

	public class OuvinteDoBotaoRegistrar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String botao = e.getActionCommand();

			switch (botao) {
				case "Registrar":
					dispose();
					new JanelaDoBotaoRegistro().janelaDeRegistro(null, botao);
					break;

				case "Logar":
					String loginDoUsuario = login.getText();
					String senhaDoUsuario = senha.getText();
					Usuario checkUsersLogin = c.recuperarUsuario(loginDoUsuario);

					if (checkUsersLogin == null)
						JOptionPane.showMessageDialog(rootPane, "Passei aqui!!! null");

					else if (checkUsersLogin.getSenha().equals(senhaDoUsuario)) {
						JOptionPane.showMessageDialog(rootPane, "Seja Bem vindo: " + loginDoUsuario);
						dispose();
					} else
						JOptionPane.showMessageDialog(rootPane, "Senha Esta (ERRADA)");
			}
		}

	}

	public void adicionarLabel(JFrame frame) {
		ImageIcon iconeT = new ImageIcon("Login2.png");
		JLabel texto = new JLabel(" Tela de Login", iconeT, JLabel.LEFT);

		texto.setBounds(130, 20, 200, 20);
		texto.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
		texto.setForeground(Color.BLACK);

		frame.add(texto);
	}

	public void textolfield(JFrame frame) {

		login = new JTextField("");
		login.setBounds(100, 75, 200, 30);
		login.addKeyListener(null);
		frame.add(login);

		senha = new JTextField("");
		senha.setBounds(100, 120, 200, 30);
		senha.addKeyListener(null);
		frame.add(senha);

	}

	public void entrada() {

		JLabel login = new JLabel("Usuario: ");
		login.setBounds(30, 75, 100, 30);
		add(login);

		JLabel senha = new JLabel("Senha: ");
		senha.setBounds(30, 120, 100, 30);
		add(senha);
	}

	public void botao() {
		OuvinteDoBotaoRegistrar ouveBotao = new OuvinteDoBotaoRegistrar();

		JButton entrar = new JButton("Logar");
		entrar.setBounds(30, 200, 100, 30);
		entrar.addActionListener(ouveBotao);
		add(entrar);

		JButton registrar = new JButton("Registrar");
		registrar.setBounds(260, 200, 100, 30);
		registrar.addActionListener(ouveBotao);
		add(registrar);

	}

}