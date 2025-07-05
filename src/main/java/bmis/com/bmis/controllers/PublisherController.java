package bmis.com.bmis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import bmis.com.bmis.models.dtos.GenreDto;
import bmis.com.bmis.models.dtos.PublisherDto;
import bmis.com.bmis.services.PublisherService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;
    @GetMapping("/list")
    public List<PublisherDto> listAll() {
        return publisherService.findAll();
    }

    @PostMapping("/store")
    public String storePublisher(@Valid @ModelAttribute PublisherDto publisherDto, BindingResult result, Model model) {
        if (publisherService.checkIfExists(publisherDto)) {
            result.addError(new FieldError("publisherDto", "name", "This Publisher already exists"));
        }

        if (result.hasErrors()) {
            model.addAttribute("publishers", publisherService.findAll());
            return "publishers/create";
        }
        publisherService.save(publisherDto);

        return "redirect:/publishers/list";
    }

    @GetMapping("/edit")
    public String editPublisher(@RequestParam("id") Long id, Model model, HttpServletRequest request) {
      
        PublisherDto publisherDto = publisherService.findDtoById(id);
        model.addAttribute("publisherDto", publisherDto);
        model.addAttribute("pageTitle", "Edit Publisher");
        return "publishers/edit";
    }

    @PostMapping("/update")
    public String updatePublisher(@Valid @ModelAttribute PublisherDto publisherDto, BindingResult result, Model model) {
        if (result.hasErrors()) {

            return "publishers/edit";
        }

        publisherService.save(publisherDto);
        return "redirect:/publishers/list";
    }

    @GetMapping("/delete")
    public String deletePublisher(@RequestParam("id") Long id) {
        publisherService.deleteById(id);
        return "redirect:/publishers/list";
    }

}