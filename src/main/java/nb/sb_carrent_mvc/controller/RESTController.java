package nb.sb_carrent_mvc.controller;

import nb.sb_carrent_mvc.dto.APICarsResponseDTO;
import nb.sb_carrent_mvc.dto.ReservationDTO;
import nb.sb_carrent_mvc.dto.SimpleResponseDTO;
import nb.sb_carrent_mvc.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class RESTController {
    private final AppService service;

    @Autowired
    public RESTController(AppService service) {
        this.service = service;
    }

    @GetMapping("/api/getcars")
    public ResponseEntity<APICarsResponseDTO> getCars(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        APICarsResponseDTO dto = service.getFreeCarsAPI(startDate, endDate);

        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/api/reserve")
    public ResponseEntity<SimpleResponseDTO> reserve(@RequestBody ReservationDTO reservationDTO) {
        SimpleResponseDTO dto = service.reserve(reservationDTO.getUserResDTO());

        return ResponseEntity.ok().body(dto);
    }
}