/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
/**
 *
 * @author allis
 */
public class CorreoService {
   
    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreoBienvenida(String destino, String nombreUsuario) {
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo(destino);
            mensaje.setSubject("Bienvenido a la Plataforma de Eventos");
            mensaje.setText("Hola " + nombreUsuario + ", tu cuenta fue creada correctamente.");
            mailSender.send(mensaje);
        } catch (Exception e) {
            System.out.println("No se pudo enviar el correo: " + e.getMessage());
        }
    } 
}
