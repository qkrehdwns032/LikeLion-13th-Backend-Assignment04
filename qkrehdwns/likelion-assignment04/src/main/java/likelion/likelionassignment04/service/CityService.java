package likelion.likelionassignment04.service;


import likelion.likelionassignment04.domain.City;
import likelion.likelionassignment04.domain.Country;
import likelion.likelionassignment04.dto.city.CityResponseDto;
import likelion.likelionassignment04.dto.city.CitySaveDto;
import likelion.likelionassignment04.repository.CityRepository;
import likelion.likelionassignment04.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    // 도시 저장
    @Transactional
    public void saveCity(CitySaveDto citySaveDto) {
       Country country = countryRepository.findById(citySaveDto.country_id())
           .orElseThrow(IllegalArgumentException::new);

        City city = City.builder()
            .city_name(citySaveDto.city_name())
            .country(country)
            .build();

        cityRepository.save(city);
    }

    //특정 국가의 도시들 조회
    @Transactional
    public List<CityResponseDto> findCitiesByCountryId(Long countryId) {
        Country country = countryRepository.findById(countryId)
            .orElseThrow(IllegalArgumentException::new);

        List<City> cities = cityRepository.findByCountry(country);

        return cities.stream()
            .map(CityResponseDto::from)
            .toList();
    }

    @Transactional
    public void updateCity(Long cityId, CitySaveDto citySaveDto) {
        City city = cityRepository.findById(cityId)
            .orElseThrow(IllegalArgumentException::new);

        city.update(citySaveDto);
    }

    @Transactional
    public void deleteCity(Long cityId) {
        City city = cityRepository.findById(cityId)
            .orElseThrow(IllegalArgumentException::new);

        cityRepository.delete(city);
    }
}
