package aquecimento;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class Hermes {

	public static void obterProgramacaoDeUmCanal(String destinario) {

		String remetente = "";
		String senha = "";
		Properties props = new Properties();
		props.put("mail.smtp.user", remetente);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "25");
		props.put("mail.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");

		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(remetente, senha);
			}
		});

		session.setDebug(false);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(remetente));

			Address[] toUser = InternetAddress.parse(destinario);

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Lista da programacao de hoje");
			message.setText("");
			FileDataSource fds = new FileDataSource("relatorio do dia.pdf");
			message.setDataHandler(new DataHandler(fds));
			message.setFileName(fds.getName());

			Transport.send(message);
			System.out.println("Feito!");

		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu alguma falha no envio!", "falha no envio",
					JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e);
		}
	}
}