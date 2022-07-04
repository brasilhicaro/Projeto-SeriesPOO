package aquecimento;

public class Usuario {
	private String login;
	private String senha;
	
	public String getLogin() {
		return login;
	}
	public String getSenha() {
		return senha;
	}
	public void validarLogin(Usuario u) throws Exception{
		Exception e = null;
		if(verificarNumero(u.getLogin())){
			throw e = new Exception("Contém números.");
		}
		else if(u.getLogin().contains(" ")) 
			throw e = new Exception("Contém Espaços.");
		else if(u.getLogin().length() < 5)
			throw e = new Exception("Login muito curto");
	}
	public void validarSenha(Usuario u) throws Exception{
		Exception e = null;
		if(u.getSenha().length() < 6)
			throw e = new Exception("Senha muito curta");
		else if(u.getLogin().equals(u.getSenha()))
			throw e = new Exception("Senha igual ao login");
		else if(!verificarUpperCase(u.getSenha())) {
			throw e = new Exception("Senha deve conter pelo menos uma "
					+ "letra maiuscula");}
		else if(!verificarNumero(u.getSenha())) 
			throw e = new Exception("Senha deve conter pelo menos um numero");
		else if(!verificarLowerCase(u.getSenha())) {
			throw e = new Exception("Senha deve conter pelo menos "
					+ "uma letra minuscula");}
		else if(verificarIgualdade(u.getSenha())) {
			throw e = new Exception("Senha contém caracteres iguais!");}
	}
	public boolean verificarIgualdade(String s) {
		boolean flag = false;
		for(int c = 0; c < s.length()-1;c++) {
			if(s.charAt(c) == s.charAt(c+1))
				flag = true;
		}
		return flag;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean verificarNumero(String s) {
		boolean flag = false;  
		for ( int i = 0; i < s.length(); i++ ) { 
				if ( Character.isDigit( s.charAt(i) ) ) {  
					flag = true;  
					break;  
				}  
			}  
		return flag;
	}
	public boolean verificarUpperCase(String s) {
		boolean flag = false;  
		for ( int i = 0; i < s.length(); i++ ) { 
				if ( Character.isUpperCase( s.charAt(i) ) ) {  
					flag = true;  
					break;  
				}  
			}  
		return flag;
	}
	public boolean verificarLowerCase(String s) {
		boolean flag = false;  
		for ( int i = 0; i < s.length(); i++ ) { 
				if ( Character.isLowerCase( s.charAt(i) ) ) {  
					flag = true;  
					break;  
				}  
			}  
		return flag;
	}
}