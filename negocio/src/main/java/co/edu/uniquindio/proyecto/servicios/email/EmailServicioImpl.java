package co.edu.uniquindio.proyecto.servicios.email;

import co.edu.uniquindio.proyecto.entidades.Persona_Usuario;
import co.edu.uniquindio.proyecto.entidades.email.Email;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.excepciones.UsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Properties;

@Service
@PropertySource("classpath:mail/mailconfig.properties")
public class EmailServicioImpl implements EmailServicio {
    @Autowired
    private SpringTemplateEngine thymeleafTemplateEngine;

    @Autowired
    private UsuarioRepo usuarioRepo;

    private JavaMailSender mailSender;

    @Value("${spring.mail.host}")
    private String mailServerHost;

    @Value("${spring.mail.port}")
    private Integer mailServerPort;

    @Value("${spring.mail.username}")
    private String mailServerUsername;

    @Value("${spring.mail.password}")
    private String mailServerPassword;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String mailSenderAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String mailServerStartTls;

    public JavaMailSender getInstance() {
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();

        mailSenderImpl.setHost(mailServerHost);
        mailSenderImpl.setPort(mailServerPort);
        mailSenderImpl.setUsername(mailServerUsername);
        mailSenderImpl.setPassword(mailServerPassword);

        Properties prop = mailSenderImpl.getJavaMailProperties();
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.auth", mailSenderAuth);
        prop.put("mail.smtp.starttls.enable", mailServerStartTls);
        prop.put("mail.debug", "false");

        return mailSenderImpl;
    }

    public Context setTemplateRecuperacion (Email email) {
        if ( mailSender == null ) {
            mailSender = getInstance();
        }

        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("nombreCompleto", email.getNombreCompleto());
        thymeleafContext.setVariable("fechaSolicitud", email.getFechaSolicitud());
        thymeleafContext.setVariable("email", email.getEmail());
        thymeleafContext.setVariable("contrasena", email.getContrasena());

        return thymeleafContext;
    }

    public Persona_Usuario obtenerUsuarioPorCorreo(String emailUsuario) throws UsuarioException {
        Optional<Persona_Usuario> usuario = usuarioRepo.findByEmail(emailUsuario);

        if ( usuario.isEmpty() ) {
            throw new UsuarioException("El correo ingresado no está registrado");
        }

        return usuario.get();
    }

    @Override
    public void recuperarContrasena(String emailUsuario) throws MessagingException, UsuarioException {
        Persona_Usuario usuario = obtenerUsuarioPorCorreo(emailUsuario);

        Email email = new Email(
                usuario.getNombreCompleto(), usuario.getEmail(), usuario.getContrasena(), Date.valueOf(LocalDate.now())
        );

        Context template = setTemplateRecuperacion(email);
        String htmlBody = thymeleafTemplateEngine.process("email-recuperacion-template.html", template);

        sendMessage(email.getEmail(), "Recuperación de contraseña UniTravel", htmlBody);
    }

    private void sendMessage(String emailTo, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("stiven13072000@gmail.com");
        helper.setTo(emailTo);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        helper.addInline("logo.jpg", new ClassPathResource("static/images/EmailLogo.jpg"));

        mailSender.send(message);
    }
}
