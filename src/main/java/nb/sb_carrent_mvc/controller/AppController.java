package nb.sb_carrent_mvc.controller;

import nb.sb_carrent_mvc.dto.*;
import nb.sb_carrent_mvc.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class AppController {
    private final AppService service;

    @Autowired
    public AppController(AppService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam(value = "apiKey", required = false) String apiKey) {
        AdminPageDTO adminPageDTO = service.getAdminPage(apiKey, null);

        if (adminPageDTO != null) {
            model.addAttribute("adminPageDTO", adminPageDTO);

            return "admin.html";
        }

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

    @GetMapping("/admin/car/edit/start")
    public String startCarEdit(Model model, @RequestParam("apiKey") String apiKey, @RequestParam("carId") Integer carId) {
        EditCarDTO editCarDTO = service.startCarEdit(apiKey, carId);

        if (editCarDTO != null) {
            model.addAttribute("editCarDTO", editCarDTO);

            return "adminEditCar.html";
        }

        AdminPageDTO adminPageDTO = service.getAdminPage(apiKey, "Wrong Car ID!");

        if (adminPageDTO != null) {
            model.addAttribute("adminPageDTO", adminPageDTO);

            return "admin.html";
        }

        model.addAttribute("simpleResponseDTO", new SimpleResponseDTO("Wrong API Key!"));

        return "index.html";
    }

    @PostMapping("/admin/car/edit/finish")
    public String finishCarEdit(Model model, EditCarDTO editCarDTO) {
        AdminPageDTO adminPageDTO = service.finishCarEdit(editCarDTO);

        if (adminPageDTO != null) {
            model.addAttribute("adminPageDTO", adminPageDTO);

            return "admin.html";
        }

        model.addAttribute("simpleResponseDTO", new SimpleResponseDTO("Wrong API Key!"));

        return "index.html";
    }

    @PostMapping("/admin/car/new")
    public String addNewCar(Model model, @RequestParam("apiKey") String apiKey, NewCarDTO newCarDTO) throws IOException {
        AdminPageDTO adminPageDTO = service.addNewCar(apiKey, newCarDTO);

        if (adminPageDTO != null) {
            model.addAttribute("adminPageDTO", adminPageDTO);

            return "admin.html";
        }

        model.addAttribute("simpleResponseDTO", new SimpleResponseDTO("Wrong API Key!"));

        return "index.html";
    }
}