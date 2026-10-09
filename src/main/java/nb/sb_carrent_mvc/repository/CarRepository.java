package nb.sb_carrent_mvc.repository;

import nb.sb_carrent_mvc.model.Car;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {
    @Query("SELECT * FROM car WHERE id NOT IN (SELECT car_id FROM reservation r WHERE :startDate BETWEEN r.start_date AND r.end_date OR :endDate BETWEEN r.start_date AND r.end_date) AND active")
    List<Car> findAvailableCars(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}