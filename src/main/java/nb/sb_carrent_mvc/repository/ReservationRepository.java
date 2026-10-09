package nb.sb_carrent_mvc.repository;

import nb.sb_carrent_mvc.model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

}