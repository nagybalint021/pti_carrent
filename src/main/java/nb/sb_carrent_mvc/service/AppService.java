package nb.sb_carrent_mvc.service;

import nb.sb_carrent_mvc.dto.*;
import nb.sb_carrent_mvc.model.Car;
import nb.sb_carrent_mvc.model.Reservation;
import nb.sb_carrent_mvc.repository.CarRepository;
import nb.sb_carrent_mvc.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

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

    private boolean checkApiKeys(String apiKey) {
        return apiKeys.contains(apiKey);
    }

    public AdminPageDTO getAdminPage(String apiKey, String response) {
        if (checkApiKeys(apiKey)) {
            return new AdminPageDTO(
                    apiKey,
                    response,
                    StreamSupport.stream(reservationRepo.findAll().spliterator(), false).map(this::convertReservationToDTO).toList(),
                    StreamSupport.stream(carRepo.findAll().spliterator(), false).map(this::convertCarToDTO).toList()
            );
        }

        return null;
    }

    public EditCarDTO startCarEdit(String apiKey, Integer carId) {
        EditCarDTO dto = null;

        if (checkApiKeys(apiKey)) {
            Optional<Car> carOpt = carRepo.findById(carId);

            if (carOpt.isPresent()) {
                CarDTO carDTO = convertCarToDTO(carOpt.get());

                dto = new EditCarDTO(apiKey, carDTO.getId(), carDTO.getType(), carDTO.getPrice(), carDTO.getImage(), carDTO.getActive());
            }
        }

        return dto;
    }

    public AdminPageDTO finishCarEdit(EditCarDTO editCarDTO) {
        String message = "Failed to edit car!";

        if (checkApiKeys(editCarDTO.getApiKey())) {
            Optional<Car> carOpt = carRepo.findById(editCarDTO.getSelectedCar().getId());

            if (carOpt.isPresent()) {
                Car car = carOpt.get();

                car.setType(editCarDTO.getSelectedCar().getType());
                car.setDailyPrice(editCarDTO.getSelectedCar().getPrice());
                car.setActive(editCarDTO.getSelectedCar().getActive());

                carRepo.save(car);

                message = "Successfully edited car!";
            }
        }

        return getAdminPage(editCarDTO.getApiKey(), message);
    }

    public AdminPageDTO addNewCar(String apiKey, NewCarDTO newCarDTO) throws IOException {
        String message = "Failed to add new car!";

        if (checkApiKeys(apiKey)) {
            carRepo.save(new Car(
                    null,
                    newCarDTO.getType(),
                    newCarDTO.getPrice(),
                    newCarDTO.getImage().getBytes(),
                    newCarDTO.getActive()
            ));

            message = "Successfully added car!";
        }

        return getAdminPage(apiKey, message);
    }

    public APICarsResponseDTO getFreeCarsAPI(LocalDate startDate, LocalDate endDate) {
        return new APICarsResponseDTO(
                carRepo.findAvailableCars(startDate, endDate).stream().map(this::convertCarToDTO).toList()
        );
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

    private ReservationDTO convertReservationToDTO(Reservation res) {
        return new ReservationDTO(
                res.getId(),
                res.getName(),
                res.getEmail(),
                res.getAddress(),
                res.getPhone(),
                res.getCarId(),
                res.getPrice(),
                res.getStartDate(),
                res.getEndDate()
        );
    }
}