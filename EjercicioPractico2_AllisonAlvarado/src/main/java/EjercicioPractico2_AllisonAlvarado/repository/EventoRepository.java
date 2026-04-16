/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package EjercicioPractico2_AllisonAlvarado.repository;
import EjercicioPractico2_AllisonAlvarado.domain.Evento;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author allis
 */
public interface EventoRepository extends JpaRepository<Evento, Long>{
    List<Evento> findByActivo(Boolean activo);

    List<Evento> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);

    List<Evento> findByNombreContainingIgnoreCase(String nombre);

    long countByActivoTrue();
}
