package util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.sun.mail.util.MailSSLSocketFactory;

public class pwdMail {
    public static boolean sendMail(String to, int id, String password)
            throws Exception {
        // 1.创建连接对象，连接到邮箱服务器
        Properties props = new Properties();
        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
        Session session = null;
        // 设置邮件服务器主机名
        props.setProperty("mail.smtp.host", "smtp.qq.com");

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("1490068580@qq.com",
                        "dfcpbppdxqifhbfi");
            }
        });
        // 2.创建邮件对象
        Message message = new MimeMessage(session);
        // 2.1设置发件人：
        message.setFrom(new InternetAddress("1490068580@qq.com"));
        // 2.2设置收件人：
        message.setRecipient(RecipientType.TO, new InternetAddress(to));
        // 2.3设置邮件的主题
        message.setSubject("来自校友闲置平台的邮件");
        // 2.4设置邮件的正文
        message.setContent(
                "<h1>来自校友闲置平台的找回密码邮件，需要找回密码请点击以下链接(若打开时被邮箱网站拦截，可复制链接前往浏览器打开)</h1><h3><a href='http://localhost:8080/CampusIDLE-WEB/findpwd?id="
                        + id + "&pwd=" + password
                        + "'>http://localhost:8080/CampusIDLE-WEB/findpwd?id="
                        + id + "&pwd=" + password + "</a></h3>",
                "text/html;charset=UTF-8");
        // 3.发送一封激活邮件
        Transport.send(message);
        return true;
    }
}
