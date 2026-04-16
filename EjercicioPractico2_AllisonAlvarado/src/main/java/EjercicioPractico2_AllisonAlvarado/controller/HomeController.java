/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioPractico2_AllisonAlvarado.controller;
import EjercicioPractico2_AllisonAlvarado.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
/**
 *
 * @author allis
 */
public class HomeController {
    @Autowired
    private EventoService eventoService;

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("eventos", eventoService.listarTodos());
        return "index";
    }

    @GetMapping("/acceso_denegado")
    public String accesoDenegado() {
        return "acceso_denegado";
    }
}
