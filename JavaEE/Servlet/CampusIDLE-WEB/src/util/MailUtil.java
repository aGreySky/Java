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

public class MailUtil {
    public static boolean sendMail(String to, String code) throws Exception {
        if (!(to.split("@")[1].equals("qq.com")
                || to.split("@")[1].equals("163.com")
                || to.split("@")[1].equals("sina.com")
                || to.split("@")[1].equals("sina.cn")
                || to.split("@")[1].equals("2008.sina.com")
                || to.split("@")[1].equals("51uc.com"))) {
            return false;
        }
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
        message.setSubject("来自校友闲置平台的激活邮件");
        // 2.4设置邮件的正文
        message.setContent(
                "<h1>来自校友闲置平台的激活邮件，激活请点击以下链接(若打开时被邮箱网站拦截，可复制链接前往浏览器打开)</h1><h3><a href='http://localhost:8080/CampusIDLE-WEB/active?code="
                        + code
                        + "'>http://localhost:8080/CampusIDLE-WEB/active?code="
                        + code + "</a></h3>",
                "text/html;charset=UTF-8");
        // 3.发送一封激活邮件
        Transport.send(message);
        return true;
    }
}
