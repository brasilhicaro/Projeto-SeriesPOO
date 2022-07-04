package aquecimento;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.DayOfWeek;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorDeRelatorios {
	public static void obterProgramacaoDeUmCanal(Canal c) {
		Document doc = new Document(PageSize.A4, 72,72,72,72);
		Persistencia p = new Persistencia();
		try {
			CentralDeInformacoes central = p.recuperar();
			OutputStream os = new FileOutputStream("relatorio.pdf");
			PdfWriter.getInstance(doc, os);
			doc.open();
			Paragraph pg = new Paragraph("Programacao do canal "+c.toString());
			pg.setAlignment(Element.ALIGN_CENTER);
			doc.add(pg);
			Paragraph separador = new Paragraph("                              ");
			doc.add(separador);
			PdfPTable tabela = new PdfPTable(1);
			
			for(ProgramaDeTv programa: central.getTodoOsProgramas()) {
				if(programa.getCanal().getIndentificador().equals(c.getIndentificador()))
					tabela.addCell(programa.getNome());
			}
			doc.add(tabela);
			doc.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(DocumentException e) {
			e.printStackTrace();
		}
	}
		public static boolean obterProgramacaoDoDia(DayOfWeek dia) {
			Document doc = new Document(PageSize.A4, 72,72,72,72);
			Persistencia p = new Persistencia();
			boolean flag = false;
			try {
				CentralDeInformacoes central = p.recuperar();
				OutputStream os = new FileOutputStream("relatorio do dia.pdf");
				PdfWriter.getInstance(doc, os);
				doc.open();
				Paragraph pg = new Paragraph("Programacao de hoje");
				pg.setAlignment(Element.ALIGN_CENTER);
				doc.add(pg);
				Paragraph separador = new Paragraph("                              ");
				doc.add(separador);
				PdfPTable tabela = new PdfPTable(1);
				for(ProgramaDeTv programa: central.getTodoOsProgramas()) {
					for(DayOfWeek dias: programa.getDia()) {
						if(dias.getValue() == dia.getValue()) {
							tabela.addCell(programa.getNome());
							flag =  true;
							
						}
					}
				}
				doc.add(tabela);
				doc.close();
				
		
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(DocumentException e) {
			e.printStackTrace();
		}
			return flag;
	}
}
