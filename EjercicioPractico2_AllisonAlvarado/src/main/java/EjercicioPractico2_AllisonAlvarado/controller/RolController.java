/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioPractico2_AllisonAlvarado.controller;
import EjercicioPractico2_AllisonAlvarado.domain.Rol;
import EjercicioPractico2_AllisonAlvarado.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/**
 *
 * @author allis
 */
@Controller
@RequestMapping("/roles")
public class RolController {
     @Autowired
    private RolService rolService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("roles", rolService.listarTodos());
        return "rol/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("rol", new Rol());
        return "rol/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Rol rol, RedirectAttributes redirectAttributes) {
        rolService.guardar(rol);
        redirectAttributes.addFlashAttribute("mensaje", "Rol guardado correctamente");
        return "redirect:/roles";
    }
@GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Rol rol = rolService.buscarPorId(id).orElseThrow();
        model.addAttribute("rol", rol);
        return "rol/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        rolService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Rol eliminado correctamente");
        return "redirect:/roles";
    }
}

