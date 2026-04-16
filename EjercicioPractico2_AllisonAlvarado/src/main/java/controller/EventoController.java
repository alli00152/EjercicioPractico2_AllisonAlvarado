/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import domain.Evento;
import service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/eventos")
/**
 *
 * @author allis
 */
public class EventoController {
     @Autowired
    private EventoService eventoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("eventos", eventoService.listarTodos());
        return "evento/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("evento", new Evento());
        return "evento/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Evento evento, RedirectAttributes redirectAttributes) {
        eventoService.guardar(evento);
        redirectAttributes.addFlashAttribute("mensaje", "Evento guardado correctamente");
        return "redirect:/eventos";
    }
     @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Evento evento = eventoService.buscarPorId(id).orElseThrow();
        model.addAttribute("evento", evento);
        return "evento/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        eventoService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Evento eliminado correctamente");
        return "redirect:/eventos";
    }
}
