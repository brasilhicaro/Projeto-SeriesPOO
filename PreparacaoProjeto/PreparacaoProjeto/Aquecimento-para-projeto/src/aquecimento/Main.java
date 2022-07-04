package aquecimento;

import java.awt.HeadlessException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		Persistencia p = new Persistencia();

		
		ConfigurarJanelaProgramaLista cJL = new ConfigurarJanelaProgramaLista();
			CentralDeInformacoes central = p.recuperar();
			String opcao = "";
			Scanner input = new Scanner(System.in);
			
			String[] tiposProgramaDeTv = {"Programa Continuo", "Serie Regular", "Reality Show"};
			String[] tipoCanais = {"Jornalismo", "Educativo", "Diversos","Esportiva"};
			String[] ListaCanais = new String[central.getTodosOsCanais().size()];
			boolean flag = false;
			String login = null;
			Usuario u = new Usuario();do {
				try {
					login = JOptionPane.showInputDialog("Insira o seu login");
					u.setLogin(login);
					u.validarLogin(u);
					flag = true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			}while(!flag);
			Usuario checkUsuario = central.recuperarUsuario(login);
			flag = false;
			if(checkUsuario != null) {
				JOptionPane.showMessageDialog(null, "Usuario ja cadastrado!");
				flag = true;
			}
			if(flag) {
				boolean testaSenha = false;
				while(!testaSenha){
					String validarSenha = JOptionPane.showInputDialog("Insira a sua senha");
					if(checkUsuario.getSenha().equals(validarSenha)) {
						testaSenha = true;
						JOptionPane.showMessageDialog(null, "Bem vindo "+ checkUsuario.getLogin());
					}else {
						JOptionPane.showMessageDialog(null, "Senha Incorreta", "", JOptionPane.ERROR_MESSAGE);
					}
					
				} 
					
					
			}
			if(!flag) {
			do {
				try {
					String senha = JOptionPane.showInputDialog("Insira a sua senha");
					u.setSenha(senha);
					u.validarSenha(u);
					flag = true;
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
			}while(!flag);
			try {
				central.adicionarUsuario(u);
				p.salvar(central);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			}
			
			while(!opcao.equals("S")) {
				System.out.println("Insira\n1 - Novo Programa\n"
							+ "2 - Listar todos os Programas\n"
							+ "3 - Listar todos os programas do mesmo tipo.\n"
							+ "4 - Novo canal de televisao\n"
							+ "5 - Listar todos os canais de televisao\n"
							+ "6 - Gerar relacao dos programas de um mesmo canal\n"
							+ "7 - Enviar relacao dos programas do dia\n"
							+ "8 - Editar Canal.\n"
							+ "'S' - Sair.");
				opcao = input.nextLine().toUpperCase();

				switch (opcao) {
				case "1":
					boolean naoTemCanal = false;
					if(central.getTodosOsCanais().size() == 0) {
						naoTemCanal = true;
					}
					if(naoTemCanal) {
						JOptionPane.showMessageDialog(null, "Cadastre um canal, antes de cadastrar um programa.",
								"Erro", JOptionPane.ERROR_MESSAGE);
					
					}else if (!naoTemCanal) {
						System.out.println("Insira o nome");
						
						String nome = input.nextLine();
						String tipo = null;
						
						boolean tipoAcabou = false;
						
						while(!tipoAcabou) {
						tipo = (String)JOptionPane.showInputDialog(null,"Selecione o tipo" , "Qual o tipo do programa",
								JOptionPane.INFORMATION_MESSAGE, null, tiposProgramaDeTv, tiposProgramaDeTv[0]);
						
						if(tipo != null && !tipo.isEmpty())
							tipoAcabou = true;
						if(tipo == null && tipo.isEmpty())
							JOptionPane.showMessageDialog(null, "Por favor, escolha um tipo", "Erro", JOptionPane.ERROR_MESSAGE);
						}
						
						ArrayList<DayOfWeek> dias = null;
					
						System.out.println("Esse programa passa quantos dias?");
						int qtdDeDias = Integer.parseInt(input.nextLine());
						dias = new ArrayList<DayOfWeek>();
						
						for(int c = 0;c< qtdDeDias;c++) {
							System.out.println("Insira 1 - Segunda feira ou até 7 - Domingo");
							int value = Integer.parseInt(input.nextLine());
							for(DayOfWeek op: dias)
								if(op.getValue() == value) {
									
								}
								else {
									dias.add(DayOfWeek.of(value));
									}
						}
					
						String canais = (String)JOptionPane.showInputDialog(null,"Selecione o Canal" , "Qual é o Canal que deseja editar?",
								JOptionPane.INFORMATION_MESSAGE, null, ListaCanais, ListaCanais[0]);
						Canal testadorCanal = (central.recuperarCanalPeloNome(canais));
						
						if(testadorCanal!=null) {
							ProgramaDeTv programa = new ProgramasContinuos(nome, dias, testadorCanal);
							if(tipo.equals("Series Regulares"))
								programa =  new SeriesRegulares(nome, dias, testadorCanal);
							else if(tipo.equals("Reality Shows"))
								programa = new RealityShows(nome, dias, testadorCanal);
							try {
								if(central.adicionarProgramaDeTv(programa)) {
									try { 
								p.salvar(central);
									} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Não foi possivel salvar.", "Erro", JOptionPane.ERROR_MESSAGE);
									}
								}
								} catch (HeadlessException e) {
									JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
							}	
						}
					}
				break;
				
				case "2":
					if(central.getTodoOsProgramas().size() == 0)
						JOptionPane.showMessageDialog(null, "Não há nenhum programa", "Erro", JOptionPane.ERROR_MESSAGE);
					else {
						
						String[] pArray = PassandoArrayListParaArrayPrograma.conversao(central.getTodoOsProgramas());
						cJL.configurarJanela(pArray, "Programas de Televisao");
						}
					break;
				case "3": 
					ArrayList<ProgramaDeTv>  pLista = new ArrayList<ProgramaDeTv>();
					
					if(central.getTodoOsProgramas().size() > 0) {
						boolean tipoAcabou = false;
						String tipo = null;
						while(!tipoAcabou) {
							tipo = (String)JOptionPane.showInputDialog(null,"Selecione o tipo" , "Qual o tipo do programa",
									JOptionPane.INFORMATION_MESSAGE, null, tiposProgramaDeTv, tiposProgramaDeTv[0]);
						
							if(tipo != null && !tipo.isEmpty())
								tipoAcabou = true;
							if(tipo == null && tipo.isEmpty())
								JOptionPane.showMessageDialog(null, "Por favor, escolha um tipo", "Erro", JOptionPane.ERROR_MESSAGE);
						}
						ProgramaDeTv programa = null;
						try {
							programa = new ProgramasContinuos(null, null, null);
							if(tipo.equals("Series Regulares")) {
								programa = new SeriesRegulares(null, null, null);
							}
							else if(tipo.equals("Reality Shows"))
								programa = new RealityShows(null, null, null);
							for(ProgramaDeTv pdt : central.getTodoOsProgramas()) {
								if(pdt.getTipo().equals(programa.getTipo())) {
									pLista.add(pdt);
								}
									
							}
							
						}catch(Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						
						}
						String[] pArray = PassandoArrayListParaArrayPrograma.conversao(pLista);
						cJL.configurarJanela(pArray, "Programa de Televisao");
					}
					break;
				case "4" :
					String indentificador = JOptionPane.showInputDialog("Insira o nome do canal;");
					String tipo = null;
					boolean tipoAcabou = false;
					String tipoCanal = null;
					while(!tipoAcabou) {
						tipoCanal = (String)JOptionPane.showInputDialog(null,"Selecione o tipo" , "Qual o tipo do Canal",
								JOptionPane.INFORMATION_MESSAGE, null, tipoCanais, tipoCanais[0]);
						if(tipoCanal != null && !tipoCanal.isEmpty())
							tipoAcabou = true;
						if(tipoCanal == null && tipoCanal.isEmpty())
							JOptionPane.showMessageDialog(null, "Por favor, escolha um tipo", "Erro", JOptionPane.ERROR_MESSAGE);
					}
					Canal c = new Jornalismo(indentificador);
					if(tipoCanal.equals("Educativo"))
						c = new Educativo(indentificador);
					else if(tipoCanal.equals("Diversos"))
						c = new Diversos(indentificador);
					else if(tipoCanal.equals("Esportivo"))
						c = new Esportivo(indentificador);
					
					boolean salvarCanal = true;
					for(Canal canal2 : central.getTodosOsCanais()) {
						if(c.equals(canal2))
							JOptionPane.showMessageDialog(null, "Erro canal existente", "Erro", JOptionPane.ERROR_MESSAGE);
							salvarCanal = false;
							}
					
					if(salvarCanal)
						try {
							central.adicionarCanal(c);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
							}
						try {
							p.salvar(central);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Não foi possivel salvar.", "Erro", JOptionPane.ERROR_MESSAGE);
						}
					break;
				case "5":
					String[] cArray = PassandoArrayListParaArrayCanal.conversao(central.getTodosOsCanais());
					central.getTodoOsProgramas();
					cJL.configurarJanela(cArray, "Canais");
					break;
				case "6":
					ListaCanais= PassandoArrayListParaArrayCanal.conversao(central.getTodosOsCanais());
					String canal = (String)JOptionPane.showInputDialog(null,"Selecione o Canal" , "Qual é o Canal",
							JOptionPane.INFORMATION_MESSAGE, null, ListaCanais, ListaCanais[0]);
					Canal canal2 =  central.recuperarCanalPeloNome(canal);
					if(canal2 == null)
						System.out.println("Esse canal não existe.");
					else
						GeradorDeRelatorios.obterProgramacaoDeUmCanal(canal2);
					break;
				case "7":
					LocalDate localDate = LocalDate.now();
					DayOfWeek day = DayOfWeek.from(localDate);
					boolean flag2 = GeradorDeRelatorios.obterProgramacaoDoDia(day);
					if(flag2) {
						System.out.println("Insira o e-mail para receber o arquivo: ");
						String email = input.nextLine();
						Hermes.obterProgramacaoDeUmCanal(email);
					}
					else {
						System.out.println("não existe nada cadastrado nesse dia.");
					}
				case "8":
					if(central.getTodosOsCanais().size() ==0)
						JOptionPane.showMessageDialog(null, "Não há canais, crie um primeiro.", "Erro", JOptionPane.ERROR_MESSAGE);
					else {
						String canais = (String)JOptionPane.showInputDialog(null,"Selecione o Canal" , "Qual é o Canal que deseja editar?",
								JOptionPane.INFORMATION_MESSAGE, null, ListaCanais, ListaCanais[0]);
						JOptionPane.showInputDialog(cJL, canais);
					}
				}//end switch
			}//end while
			input.close();
	}//end main
}