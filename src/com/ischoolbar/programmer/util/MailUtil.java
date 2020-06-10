package com.ischoolbar.programmer.util;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailUtil {

	// 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
	// PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
	//      对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
	public static String account = "……@163.com";
	public static String password = "……";//这里为你的邮箱授权码

	// 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
	// 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
	public static String myEmailSMTPHost = "smtp.163.com";

	/**
	 * 发送邮件的方法
	 * 
	 * @param to
	 *            邮件的接收方
	 * @param code
	 *            邮件的激活码
	 */
	public void sendMail(String to, String code) {
		// 1.创建连接对象，链接到邮箱服务器
		Properties props = new Properties();// 参数配置
		props.setProperty("mail.transport.protocol", "smtp");// 使用的协议（JavaMail规范要求）
		props.setProperty("mail.smtp.host", myEmailSMTPHost);// 发件人的邮箱的 SMTP
																// 服务器地址
		props.setProperty("mail.smtp.auth", "true");// 需要请求认证

		// 2.根据配置创建会话对象, 用于和邮件服务器交互
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(account, password);
			}
		});

		try {
			// 3.创建邮件对象
			Message message = new MimeMessage(session);
			// 3.1设置发件人
			message.setFrom(new InternetAddress(account));
			// 3.2设置收件人
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			// 3.3设置邮件的主题
			message.setSubject("来自湘潭大学考研资讯网的激活邮件");
			// 3.4设置邮件的正文
			// message.setContent("<h1>来自智慧电梯的激活邮件，您的验证码是：</h1><h3><a href='http://localhost:10080/Demo_JavaMail/active?code="
			// + code + "'>http://localhost:10080/Demo_JavaMail/active?code=" +
			// code + "</h3>", "text/html;charset=UTF-8");
			message.setContent("<h1>来自湘潭大学考研资讯网的激活邮件，您的验证码是：" + code,
					"text/html;charset=UTF-8");
			// 4.发送邮件
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送找回密码邮件的方法
	 * 
	 * @param to
	 *            邮件的接收方
	 * @param code
	 *            邮件的验证码
	 */
	public void findPasswordMail(String to, String code) {
		// 1.创建连接对象，链接到邮箱服务器
		Properties props = new Properties(); // 参数配置
		props.setProperty("mail.transport.protocol", "smtp");// 使用的协议（JavaMail规范要求）
		props.setProperty("mail.smtp.host", myEmailSMTPHost);// 发件人的邮箱的 SMTP
																// 服务器地址
		props.setProperty("mail.smtp.auth", "true"); // 需要请求认证

		// 2.根据配置创建会话对象, 用于和邮件服务器交互
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(account, password);
			}
		});

		try {
			// 3.创建邮件对象
			Message message = new MimeMessage(session);
			// 3.1设置发件人
			message.setFrom(new InternetAddress(account));
			// 3.2设置收件人
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			// 3.3设置邮件的主题
			message.setSubject("来自湘潭大学考研资讯网的验证邮件");
			// 3.4设置邮件的正文
			message.setContent("<h1>来自湘潭大学考研资讯网的验证邮件，您的验证码是：" + code,
					"text/html;charset=UTF-8");
			// 4.发送邮件
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 创建一封只包含文本的简单邮件
	 *
	 * @param session
	 *            和服务器交互的会话
	 * @param sendMail
	 *            发件人邮箱
	 * @param receiveMail
	 *            收件人邮箱
	 * @return
	 * @throws Exception
	 */
	public MimeMessage createMimeMessage(Session session,
			String sendMail, String receiveMail) throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);

		// 2. From: 发件人
		message.setFrom(new InternetAddress(sendMail, "某宝网", "UTF-8"));

		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(
				receiveMail, "XX用户", "UTF-8"));

		// 4. Subject: 邮件主题
		message.setSubject("打折钜惠", "UTF-8");

		// 5. Content: 邮件正文（可以使用html标签）
		message.setContent("XX用户你好, 今天全场5折, 快来抢购, 错过今天再等一年。。。",
				"text/html;charset=UTF-8");

		// 6. 设置发件时间
		message.setSentDate(new Date());

		// 7. 保存设置
		message.saveChanges();

		return message;
	}

//	public static void main(String[] args) {
//		 MailUtil mailUtil = new MailUtil();
//
//		 mailUtil.sendMail("……@qq.com", "zjp");//接收方  接受码
//
//	}
}

