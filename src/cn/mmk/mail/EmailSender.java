package cn.mmk.mail;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class EmailSender {
    private static final String charset = "GBK";
    private static final String defaultMimetype = "text/plain";
    
    public static void main(String[] args) throws Exception {
    	EmailSender.send(new String[]{"job@itcast.cn"}, "�ʼ�����xx", "<b>���ǲ���</b>", null , "text/html");
    }
    /**
     * �����ʼ�
     * @param receiver �ռ���
     * @param subject ����
     * @param mailContent �ʼ�����
     * @param mimetype �������� Ĭ��Ϊtext/plain,���Ҫ����HTML����,Ӧ����Ϊtext/html
     */
    public static void send(String receiver, String subject, String mailContent, String mimetype) {
    	send(new String[]{receiver}, subject, mailContent, mimetype);
    }
    /**
     * �����ʼ�
     * @param receivers �ռ���
     * @param subject ����
     * @param mailContent �ʼ�����
     * @param mimetype �������� Ĭ��Ϊtext/plain,���Ҫ����HTML����,Ӧ����Ϊtext/html
     */
    public static void send(String[] receivers, String subject, String mailContent, String mimetype) {
    	send(receivers, subject, mailContent, null, mimetype);
    }
    /**
     * �����ʼ�
     * @param receivers �ռ���
     * @param subject ����
     * @param mailContent �ʼ�����
     * @param attachements ����
     * @param mimetype �������� Ĭ��Ϊtext/plain,���Ҫ����HTML����,Ӧ����Ϊtext/html
     */
    public static void send(String[] receivers, String subject, String mailContent, File[] attachements, String mimetype) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "mail.itcast.cn");//smtp��������ַ sohu
        props.put("mail.smtp.auth", "true");//��ҪУ��
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("job@itcast.cn","lihuoming");//��¼�û���/����
            }
        });
        session.setDebug(true);
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("job@itcast.cn"));//�������ʼ�

            InternetAddress[] toAddress = new InternetAddress[receivers.length];
            for (int i=0; i<receivers.length; i++) {
                toAddress[i] = new InternetAddress(receivers[i]);
            }
            mimeMessage.setRecipients(Message.RecipientType.TO, toAddress);//�ռ����ʼ�
            mimeMessage.setSubject(subject, charset);
            
            Multipart multipart = new MimeMultipart();
            //����
            MimeBodyPart body = new MimeBodyPart();
           // body.setText(message, charset);��֧��html
            body.setContent(mailContent, (mimetype!=null && !"".equals(mimetype) ? mimetype : defaultMimetype)+ ";charset="+ charset);
            multipart.addBodyPart(body);//��������
            //����
            if(attachements!=null){
	            for (File attachement : attachements) {
	                MimeBodyPart attache = new MimeBodyPart();
	               //ByteArrayDataSource bads = new ByteArrayDataSource(byte[],"application/x-any");
	                attache.setDataHandler(new DataHandler(new FileDataSource(attachement)));
	                String fileName = getLastName(attachement.getName());
	                attache.setFileName(MimeUtility.encodeText(fileName, charset, null));
	                multipart.addBodyPart(attache);
	            }
            }
            mimeMessage.setContent(multipart);
           // SimpleDateFormat formcat = new SimpleDateFormat("yyyy-MM-dd");            
            mimeMessage.setSentDate(new Date());//formcat.parse("2010-5-23")
            Transport.send(mimeMessage);            
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    private static String getLastName(String fileName) {
        int pos = fileName.lastIndexOf("\\");
        if (pos > -1) {
            fileName = fileName.substring(pos + 1);
        }
        pos = fileName.lastIndexOf("/");
        if (pos > -1) {
            fileName = fileName.substring(pos + 1);
        }
        return fileName;
    }
}
