/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioPractico2_AllisonAlvarado.controller;
import EjercicioPractico2_AllisonAlvarado.service.EventoService;
import EjercicioPractico2_AllisonAlvarado.service.UsuarioService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consultas")
/**
 *
 * @author allis
 */
public class ConsultaController {
     @Autowired
    private EventoService eventoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String mostrar(Model model) {
        model.addAttribute("conteoActivos", eventoService.contarActivos());
        return "consulta/listado";
    }

    @PostMapping("/eventos-estado")
    public String consultarPorEstado(@RequestParam Boolean activo, Model model) {
        model.addAttribute("eventosPorEstado", eventoService.buscarPorEstado(activo));
        model.addAttribute("conteoActivos", eventoService.contarActivos());
        return "consulta/listado";
    }

}
