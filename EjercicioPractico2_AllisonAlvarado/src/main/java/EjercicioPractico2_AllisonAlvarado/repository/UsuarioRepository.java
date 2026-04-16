/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package EjercicioPractico2_AllisonAlvarado.repository;
import EjercicioPractico2_AllisonAlvarado.domain.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author allis
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
     Optional<Usuario> findByEmail(String email);

    List<Usuario> findByRolNombre(String nombreRol);
}

