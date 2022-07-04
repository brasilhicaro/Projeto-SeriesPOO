package aquecimento;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class SeriesRegulares extends ProgramaDeTv{

	public SeriesRegulares(String nome, ArrayList<DayOfWeek> dia, Canal canal) {
		super(nome, dia, canal);
	}
	public String getTipo() {
		return "SerieRegulares";
	}
}
