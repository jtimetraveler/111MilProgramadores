package company.juancho.congresoFP.tools;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
//import android.service.textservice.SpellCheckerService;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailJob extends AsyncTask<MailJob.Mail,Void,Void> {
    private final String user;
    private final String pass;
    private final Activity context;

    public MailJob(Activity context, String user, String pass) {
        super();
        this.context = context;
        this.user=user;
        this.pass=pass;
    }

    @Override
    protected Void doInBackground(Mail... mails) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pass);
                    }
                });
        for (Mail mail:mails) {

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(mail.from));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(mail.to));
                message.setSubject(mail.subject);
                message.setText(mail.content);

                Transport.send(message);
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        Toast.makeText(context, "Revise su casilla de correo", Toast.LENGTH_LONG).show();

                    }
                });

            } catch (MessagingException e) {
                Log.d("MailJob", e.getMessage());
            }
        }
        return null;
    }

    public static class Mail{
        private final String subject ;
        private final String content ;
        private final String from ;
        private final String to;


        public Mail(String from, String to, String subject, String content){
            this.subject=subject;
            this.content=content;
            this.from=from;
            this.to=to;
        }
    }
}


