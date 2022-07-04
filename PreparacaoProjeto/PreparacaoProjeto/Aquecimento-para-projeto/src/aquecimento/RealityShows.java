package aquecimento;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class RealityShows extends ProgramaDeTv {

	public RealityShows(String nome, ArrayList<DayOfWeek> dia, Canal canal) {
		super(nome, dia, canal);
	}
	public String getTipo() {
		return "RealityShows";
	}
}
