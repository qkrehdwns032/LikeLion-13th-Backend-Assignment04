package likelion.likelionassignment04.service;

import likelion.likelionassignment04.domain.Country;
import likelion.likelionassignment04.dto.country.CountryResponseDto;
import likelion.likelionassignment04.dto.country.CountrySaveDto;
import likelion.likelionassignment04.repository.CityRepository;
import likelion.likelionassignment04.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

    // 국가 저장
    @Transactional
    public void saveCountry(CountrySaveDto countrySaveDto) {
        Country country = Country.builder()
            .country_name(countrySaveDto.country_name())
            .build();

        countryRepository.save(country);
    }

    //모든 국가 조회
    @Transactional
    public Page<CountryResponseDto> findAllCountries(Pageable pageable) {
        Page<Country> cities = countryRepository.findAll(pageable);

        return cities.map(CountryResponseDto::from);
    }

    //단일 국가 조회
    @Transactional
    public CountryResponseDto findCountryById(Long countryId) {
        Country country = countryRepository
            .findById(countryId)
            .orElseThrow(() -> new IllegalArgumentException("해당 국가를 찾을 수 없습니다."));

        return CountryResponseDto.from(country);
    }

    @Transactional
    public void updateCountry(Long countryId, CountrySaveDto countrySaveDto) {
        Country country = countryRepository.findById(countryId)
            .orElseThrow(() -> new IllegalArgumentException("해당 국가를 찾을 수 없습니다."));

        country.update(countrySaveDto);
    }

    @Transactional
    public void deleteCountry(Long countryId) {
        Country country = countryRepository.findById(countryId)
            .orElseThrow(() -> new IllegalArgumentException("해당 국가를 찾을 수 없습니다."));

        countryRepository.delete(country);
    }

}
