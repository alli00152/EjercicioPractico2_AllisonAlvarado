/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import domain.Evento;
import repository.EventoRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author allis
 */
@Service
public class EventoService {
    
    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> buscarPorId(Long id) {
        return eventoRepository.findById(id);
    }

    public Evento guardar(Evento evento) {
        return eventoRepository.save(evento);
    }

    public void eliminar(Long id) {
        eventoRepository.deleteById(id);
    }

    public List<Evento> buscarPorEstado(Boolean activo) {
        return eventoRepository.findByActivo(activo);
    }
     public List<Evento> buscarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return eventoRepository.findByFechaBetween(fechaInicio, fechaFin);
    }

    public List<Evento> buscarPorNombre(String nombre) {
        return eventoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public long contarActivos() {
        return eventoRepository.countByActivoTrue();
    }
}
