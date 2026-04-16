/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioPractico2_AllisonAlvarado.service;
import EjercicioPractico2_AllisonAlvarado.domain.Usuario;
import EjercicioPractico2_AllisonAlvarado.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
/**
 *
 * @author allis
 */
@Service
public class UsuarioService {
     @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CorreoService correoService;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
     public Usuario guardar(Usuario usuario, boolean esNuevo) {
        if (esNuevo || usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        } else {
            Usuario actual = usuarioRepository.findById(usuario.getId()).orElse(null);
            if (actual != null) {
                usuario.setPassword(actual.getPassword());
            }
        }

        Usuario guardado = usuarioRepository.save(usuario);

        if (esNuevo) {
            correoService.enviarCorreoBienvenida(guardado.getEmail(), guardado.getNombre());
        }

        return guardado;
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
      public List<Usuario> buscarPorRol(String nombreRol) {
        return usuarioRepository.findByRolNombre(nombreRol);
    }
}
