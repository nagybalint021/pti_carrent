package nb.sb_carrent_mvc.controller;

import nb.sb_carrent_mvc.dto.*;
import nb.sb_carrent_mvc.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    private final AppService service;

    @Autowired
    public AppController(AppService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/cars")
    public String getFreeCars(Model model, ResDateDTO resDateDTO) {
        FreeCarsDTO freeCarsDTO = service.getFreeCars(resDateDTO);

        if (freeCarsDTO == null) {
            model.addAttribute("simpleResponseDTO", new SimpleResponseDTO("Wrong date range."));
        } else {
            model.addAttribute("freeCarsDTO", freeCarsDTO);
        }

        return "index.html";
    }

    @GetMapping("/cars/res/start")
    public String startReservation(Model model, UserSelectionDTO userSelectionDTO) {
        userSelectionDTO = service.startReservation(userSelectionDTO);

        model.addAttribute("userSelectionDTO", userSelectionDTO);

        return "res.html";
    }

    @PostMapping("/cars/res/finish")
    public String finishReservation(Model model, UserResDTO userResDTO) {
        SimpleResponseDTO simpleResponseDTO = service.reserve(userResDTO);

        model.addAttribute("simpleResponseDTO", simpleResponseDTO);

        return "index.html";
    }
}