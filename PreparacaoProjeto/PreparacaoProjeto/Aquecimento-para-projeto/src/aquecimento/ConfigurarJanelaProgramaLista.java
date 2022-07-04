package aquecimento;

import javax.swing.JFrame;
import javax.swing.JList;

public class ConfigurarJanelaProgramaLista extends JFrame{
	public void configurarJanela(String[]  pLista, String tipo) {
		
	
		
		
		
		setTitle("Lista de "+tipo);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		JList lista = new JList(pLista);
		add(lista);
	}
}
