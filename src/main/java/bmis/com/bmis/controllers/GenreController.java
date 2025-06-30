package bmis.com.bmis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import bmis.com.bmis.models.dtos.GenreDto;
import bmis.com.bmis.services.GenreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService; 

    @GetMapping("/list")
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("genres", genreService.findAll());
        return "genres/list"; 
    }
   
    @GetMapping("/add")
    public String addGenre(Model model, HttpServletRequest request) {
        model.addAttribute("genreDto", new GenreDto());
        model.addAttribute("pageTitle",  "Add new Genre");
        return "genres/create";
    }

    @PostMapping("/store")
    public String storeGenre(@Valid @ModelAttribute GenreDto genreDto, BindingResult result, Model model) {
        if (genreService.checkIfExists(genreDto)) {
            result.addError(new FieldError("genreDto", "name", "This Genre already exists"));
        }

        if (result.hasErrors()) {
            model.addAttribute("genres", genreService.findAll());
            return "genres/create";
        }
        genreService.save(genreDto);
       
        return "redirect:/genres/list";
    }

  
    @GetMapping("/edit")
    public String editGenre(@RequestParam("id") Long id, Model model, HttpServletRequest request) {
       
        GenreDto genreDto = genreService.findDtoById(id);
        model.addAttribute("genreDto",   genreDto);
        model.addAttribute("pageTitle",  "Edit Genre");
        return "genres/edit";
    }

    @PostMapping("/update")
    public String updateGenre(@Valid @ModelAttribute GenreDto genreDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
           
            return "genres/edit";
        }

        genreService.save(genreDto);
        return "redirect:/genres/list";
    }

    @GetMapping("/delete")
    public String deleteGenre(@RequestParam("id") Long id) {
        genreService.deleteById(id);
        return "redirect:/genres/list";
    }
}
