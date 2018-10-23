import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailMessage {
    private String from;
    private LinkedList<String> to;
    private String subject;
    private String content;
    private String mimeType;
    private LinkedList<String> cc;
    private LinkedList<String> bcc;

    protected EmailMessage() {
        to = new LinkedList<>();
        cc = new LinkedList<>();
        bcc = new LinkedList<>();
    }

    public static class Builder {

        EmailMessage em = new EmailMessage();

        public EmailMessage build() throws Exception{
            if(em.from != null && !em.from.isEmpty() &&  !em.to.isEmpty())
                return em;
            else
                throw new Exception("Message not ready");
        }

        public Builder addFrom(String email_from) {
            Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
            Matcher mat = pattern.matcher(email_from);

            if(mat.matches()){
                em.from = email_from;
            }else{
                System.out.println("Invalid email address");
            }
            return this;
        }

        public Builder addTo(String email_to) {
            Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
            Matcher mat = pattern.matcher(email_to);

            if(mat.matches()){
                em.to.add(email_to);
            }else{
                System.out.println("Invalid email address");
            }
            return this;
        }

        public Builder addSubject(String subject) {
            em.subject = subject;
            return this;
        }

        public Builder addContent(String content) {
            em.content = content;
            return this;
        }

        public Builder addCC(String cc) {
            Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
            Matcher mat = pattern.matcher(cc);

            if(mat.matches() && em.to.contains(cc)){
                em.cc.add(cc);
            }else{
                System.err.println("Invalid email address");
                System.exit(1);
            }
            return this;
        }

        public Builder addBCC(String bcc) {
            Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
            Matcher mat = pattern.matcher(bcc);

            if(mat.matches() && em.to.contains(bcc)){
                em.bcc.add(bcc);
            }else{
                System.err.println("Invalid email address");
                System.exit(1);
            }
            return this;
        }
    }

    public static Builder builder() {
        return new EmailMessage.Builder();
    }

    public void send(String password){
        String host = "smtp.poczta.onet.pl";
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);

        try
        {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(from));

            msg.setSubject(subject, "UTF-8");

            msg.setText(content, "UTF-8");

            msg.setSentDate(new Date());

            for (int i = 0; i < to.size(); ++i) {
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to.get(i)));
            }

            for (int i=0; i < cc.size(); ++i) {
                msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc.get(i)));
            }

            for (int i=0; i < cc.size(); ++i) {
                msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(cc.get(i)));
            }

            Transport.send(msg);
            System.out.println("EMail Sent Successfully!!");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
