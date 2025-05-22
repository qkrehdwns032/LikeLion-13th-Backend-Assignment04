package likelion.likelionassignment04.repository;


import likelion.likelionassignment04.domain.City;
import likelion.likelionassignment04.domain.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByCountry(Country country);
}
