package it.camille.utils;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发送邮件工具类
 * 
 * @author Camille
 * @version 1.0,2016-12-16 17:35:30
 */
public class MailUtils {

	/** 工具类私有构造方法 */
	private MailUtils() {
	}

	/**
	 * 发送邮件方法
	 * 
	 * @param email 目标邮箱
	 * @param code 验证码
	 */
	public static void sendMail(String email, String code) {

		// 获取邮箱连接对象
		Properties prop = new Properties();
		Session session = javax.mail.Session.getInstance(prop, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("camille@estore.com", "camille");
			}
		});

		// 获取邮件对象
		Message message = new MimeMessage(session);

		// 编写邮件信息并发送
		try {
			message.setFrom(new InternetAddress("camille@estore.com"));
			message.setRecipient(RecipientType.TO, new InternetAddress(email));
			message.setSubject("来自Camille书店的激活邮件");
			message.setContent(
					"<h1>来自Camille书店的激活邮件:请点击下方激活</h1><h3><a href='http://localhost:8080/newBookstore/userServlet?method=active&code="
							+ code + "&email=" + email + "'>激活点我</a></h3>",
					"text/html;charset=UTF-8");
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
