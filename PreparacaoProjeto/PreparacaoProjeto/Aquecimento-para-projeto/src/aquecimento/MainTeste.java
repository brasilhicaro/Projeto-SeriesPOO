package aquecimento;

import java.util.ArrayList;

public class MainTeste {
	public static void main(String[] args) {

		JanelaDoBotaoRegistro reg = new JanelaDoBotaoRegistro();
		ConfigurarJanelaProgramaLista cJL = new ConfigurarJanelaProgramaLista();
		JanelaDeLogin janela = new JanelaDeLogin();

		ProgramaDeTv p = new RealityShows("fidsfhis", null, null);
		RealityShows e = new RealityShows("sdfndsfd", null, null);
		ArrayList<ProgramaDeTv> pdt = new ArrayList<>();
		pdt.add(p);
		pdt.add(e);
		String[] programasDeTvs = PassandoArrayListParaArrayPrograma.conversao(pdt);

		janela.JanelaGerador();

	}
}
