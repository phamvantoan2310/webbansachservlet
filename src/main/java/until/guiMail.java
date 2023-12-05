package until;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//mail: hgffhchdu@gmail.com
//password: enkdlcwkehcphnjl


public class guiMail {
	private static String nguoiGui = "hgffhchdu@gmail.com";
	private static String matKhau = "enkdlcwkehcphnjl";
	
	public static boolean sendEmail(String nguoiNhan, String tieuDe, String noiDung) {
		//set properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		
		//set Authenticator
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(nguoiGui, matKhau);
			}
		};
		
		Session session = Session.getInstance(properties, auth);
		
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.setFrom(nguoiGui);
			msg.setRecipients(Message.RecipientType.TO, nguoiNhan);
			msg.setSubject(tieuDe);
			msg.setContent(noiDung,"text/HTML; charset=UTF-8");
			msg.setReplyTo(InternetAddress.parse(nguoiGui, false));
			
			Transport.send(msg);
			System.out.println("ok");
			return true;
		} catch (Exception e) {
			System.out.println("not ok");
			e.printStackTrace();
			return false;
		}
	}
}
