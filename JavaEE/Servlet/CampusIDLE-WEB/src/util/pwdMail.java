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
        // 1.�������Ӷ������ӵ����������
        Properties props = new Properties();
        // ����debug����
        props.setProperty("mail.debug", "true");
        // ���ͷ�������Ҫ�����֤
        props.setProperty("mail.smtp.auth", "true");
        // �����ʼ�Э������
        props.setProperty("mail.transport.protocol", "smtp");
        Session session = null;
        // �����ʼ�������������
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
        // 2.�����ʼ�����
        Message message = new MimeMessage(session);
        // 2.1���÷����ˣ�
        message.setFrom(new InternetAddress("1490068580@qq.com"));
        // 2.2�����ռ��ˣ�
        message.setRecipient(RecipientType.TO, new InternetAddress(to));
        // 2.3�����ʼ�������
        message.setSubject("����У������ƽ̨���ʼ�");
        // 2.4�����ʼ�������
        message.setContent(
                "<h1>����У������ƽ̨���һ������ʼ�����Ҫ�һ�����������������(����ʱ��������վ���أ��ɸ�������ǰ���������)</h1><h3><a href='http://localhost:8080/CampusIDLE-WEB/findpwd?id="
                        + id + "&pwd=" + password
                        + "'>http://localhost:8080/CampusIDLE-WEB/findpwd?id="
                        + id + "&pwd=" + password + "</a></h3>",
                "text/html;charset=UTF-8");
        // 3.����һ�⼤���ʼ�
        Transport.send(message);
        return true;
    }
}
