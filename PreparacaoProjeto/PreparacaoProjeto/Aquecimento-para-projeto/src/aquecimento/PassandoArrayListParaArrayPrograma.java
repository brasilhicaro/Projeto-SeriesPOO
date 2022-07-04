package aquecimento;

import java.util.ArrayList;

public class PassandoArrayListParaArrayPrograma{
	public static String[] conversao(ArrayList<ProgramaDeTv> array) {
		String[] p = new String[array.size()];
		for(int c=0; c< array.size() ;c++) {
			if(array.get(c)!=null)
				p[c] = array.get(c).toString();
		}
		return p;
	}
}
