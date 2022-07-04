package aquecimento;

import java.util.ArrayList;

public class CentralDeInformacoes {
	private ArrayList<ProgramaDeTv> todosOsProgramas;
	private ArrayList<Canal> todosOsCanais;
	private ArrayList<Usuario> todosOsUsuarios;

	public CentralDeInformacoes() {
		this.todosOsProgramas = new ArrayList<ProgramaDeTv>();
		this.todosOsCanais = new ArrayList<Canal>();
		this.todosOsUsuarios = new ArrayList<Usuario>();
	}

	public boolean adicionarProgramaDeTv(ProgramaDeTv newP) throws Exception {
		Exception e = null;
		if (newP == null)
			throw e = new Exception("Programa inválido");
		if (recuperarProgramaDeTv(newP.getId()) != null)
			throw e = new Exception("Programa já existente");
		todosOsProgramas.add(newP);
		return true;
	}

	public boolean adicionarCanal(Canal c) throws Exception {
		Exception e = null;
		if (c == null)
			throw e = new Exception("Canal inválido");
		if (recuperarCanalPeloNome(c.getIndentificador()) != null) {
			throw e = new Exception("Canal já existente");
		}
		todosOsCanais.add(c);
		return true;
	}

	public boolean adicionarUsuario(Usuario u) throws Exception {
		Exception e = null;
		if (u == null)
			throw e = new Exception("Usuario inválido");
		todosOsUsuarios.add(u);
		return true;
	}

	public Canal recuperarCanalPeloNome(String s) {
		Canal canal = null;

		for (Canal canais : todosOsCanais) {
			if (canais.getIndentificador().equals(s))
				canal = canais;
		}
		return canal;
	}

	public ProgramaDeTv recuperarProgramaDeTv(long id) {
		ProgramaDeTv programa = null;

		for (ProgramaDeTv pdt : todosOsProgramas) {
			long testarId = pdt.getId();
			if (id == testarId) {
				programa = pdt;
			}
		}
		return programa;
	}

	public Usuario recuperarUsuario(String login) {

		Usuario u = null;
		for (Usuario usuario : getTodosOsUsuarios()) {
			String testaLogin = usuario.getLogin();
			if (testaLogin.equals(login)) {
				u = usuario;
			}
		}
		return u;
	}

	public ArrayList<Usuario> getTodosOsUsuarios() {
		return todosOsUsuarios;
	}

	public ArrayList<ProgramaDeTv> getTodoOsProgramas() {
		return todosOsProgramas;
	}

	public ArrayList<Canal> getTodosOsCanais() {
		return todosOsCanais;
	}
}