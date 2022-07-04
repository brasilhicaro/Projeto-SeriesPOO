package aquecimento;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.*;

public class Persistencia {

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
	private File arquivo = new File("dados-central.xml");

	public void salvar(CentralDeInformacoes central) {

		String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>\n";
		xml += xstream.toXML(central);

		try {
			PrintWriter gravar = new PrintWriter(arquivo);
			gravar.print(xml);
			gravar.close();	

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public CentralDeInformacoes recuperar() {

		try {

			if (arquivo.exists()) {
				FileInputStream fis = new FileInputStream(arquivo);
				xstream.addPermission(AnyTypePermission.ANY);
				return (CentralDeInformacoes) xstream.fromXML(fis);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new CentralDeInformacoes();
	}
}
