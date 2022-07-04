package aquecimento;

import java.time.DayOfWeek;
import java.util.ArrayList;

public abstract class ProgramaDeTv {
    private long id;
    private String nome;
    private ArrayList<DayOfWeek> dia;
    private Canal canal;
    public ProgramaDeTv(String nome, ArrayList<DayOfWeek> dia, Canal canal){
        this.nome = nome;
        this.dia = dia;
        this.canal = canal;
        this.id = System.currentTimeMillis();
    }
    public long getId() {
        return id;
    }

    public String getNome() {

        return nome;
    }
    

    public void setNome(String nome) {

        this.nome = nome;
    }

	public ArrayList<DayOfWeek> getDia() {
		return dia;
	}
	public String toString(){
		return getNome();
	}
	public boolean equals(ProgramaDeTv pdt) {
		return this.id ==  pdt.getId();
	}
	public Canal getCanal() {
		return canal;
	}
	public abstract String getTipo();
}
