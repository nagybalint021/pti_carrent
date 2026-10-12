package nb.sb_carrent_mvc.service;

import nb.sb_carrent_mvc.dto.*;
import nb.sb_carrent_mvc.model.Car;
import nb.sb_carrent_mvc.model.Reservation;
import nb.sb_carrent_mvc.repository.CarRepository;
import nb.sb_carrent_mvc.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class AppService {
    @Value("${apiKeys}")
    private final List<String> apiKeys;
    private final CarRepository carRepo;
    private final ReservationRepository reservationRepo;

    @Autowired
    public AppService(List<String> apiKeys, CarRepository carRepo, ReservationRepository reservationRepo) {
        this.apiKeys = apiKeys;
        this.carRepo = carRepo;
        this.reservationRepo = reservationRepo;
    }

    public FreeCarsDTO getFreeCars(ResDateDTO resDateDTO) {
        if (resDateDTO == null || resDateDTO.getStartDate() == null || resDateDTO.getEndDate() == null ||
                resDateDTO.getEndDate().isBefore(resDateDTO.getStartDate())) {
            return null;
        }

        return new FreeCarsDTO(
                carRepo.findAvailableCars(resDateDTO.getStartDate(), resDateDTO.getEndDate()).stream().map(this::convertCarToDTO).toList(),
                resDateDTO
        );
    }

    public UserSelectionDTO startReservation(UserSelectionDTO userSelectionDTO) {
        carRepo.findById(userSelectionDTO.getCarId()).ifPresent(car -> {
            userSelectionDTO.setTotalPrice(car.getDailyPrice() * userSelectionDTO.getRentDays());
        });

        return userSelectionDTO;
    }

    public SimpleResponseDTO reserve(UserResDTO userResDTO) {
        SimpleResponseDTO dto = new SimpleResponseDTO("Failed to reserve car.");

        if (doReservation(userResDTO)) {
            dto = new SimpleResponseDTO("Successful reservation.");
        }

        return dto;
    }

    private boolean doReservation(UserResDTO userResDTO) {
        boolean result = false;

        if (carRepo.findAvailableCars(userResDTO.getUserSelectionDTO().getResDateDTO().getStartDate(), userResDTO.getUserSelectionDTO().getResDateDTO().getEndDate())
                        .stream().anyMatch(car -> car.getId().equals(userResDTO.getUserSelectionDTO().getCarId()))) {
            reservationRepo.save(convertUserResDTOToReservation(userResDTO));

            result = true;
        }

        return result;
    }

    private CarDTO convertCarToDTO(Car car) {
        return new CarDTO(
                car.getId(),
                car.getType(),
                car.getDailyPrice(),
                car.getImage() != null ? Base64.getEncoder().encodeToString(car.getImage()) : null,
                car.getActive()
        );
    }

    private Reservation convertUserResDTOToReservation(UserResDTO userResDTO) {
        return new Reservation(
                null,
                userResDTO.getUserSelectionDTO().getCarId(),
                userResDTO.getName(),
                userResDTO.getEmail(),
                userResDTO.getAddress(),
                userResDTO.getPhone(),
                userResDTO.getUserSelectionDTO().getResDateDTO().getStartDate(),
                userResDTO.getUserSelectionDTO().getResDateDTO().getEndDate(),
                userResDTO.getUserSelectionDTO().getTotalPrice()
        );
    }
}