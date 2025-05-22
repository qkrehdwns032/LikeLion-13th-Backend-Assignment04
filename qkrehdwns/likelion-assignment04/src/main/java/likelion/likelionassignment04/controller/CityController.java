package likelion.likelionassignment04.controller;

import jakarta.validation.Valid;
import likelion.likelionassignment04.common.error.SuccessCode;
import likelion.likelionassignment04.common.template.ApiResTemplate;
import likelion.likelionassignment04.dto.city.CityResponseDto;
import likelion.likelionassignment04.dto.city.CitySaveDto;
import likelion.likelionassignment04.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    // 도시 저장
    @PostMapping("/city/save")
    public ApiResTemplate<String> saveCity(@RequestBody @Valid CitySaveDto citySaveDto) {
        cityService.saveCity(citySaveDto);
        return ApiResTemplate.successResponse(SuccessCode.CITY_SAVE_SUCCESS,SuccessCode.CITY_SAVE_SUCCESS.getMessage());
    }

    // 특정 국가의 도시들 조회
    @GetMapping("/city/{countryId}")
    public ApiResTemplate<List<CityResponseDto>> findCitiesByCountryId(@PathVariable Long countryId) {

        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, cityService.findCitiesByCountryId(countryId));
    }

    @PatchMapping("/city/{cityId}")
    public ApiResTemplate<String> updateCity(@PathVariable Long cityId,
                                              @RequestBody CitySaveDto citySaveDto) {
        cityService.updateCity(cityId, citySaveDto);
        return ApiResTemplate.successResponse(SuccessCode.CITY_UPDATE_SUCCESS, SuccessCode.CITY_UPDATE_SUCCESS.getMessage());
    }

    @DeleteMapping("/city/{cityId}")
    public ApiResTemplate<String> deleteCity(@PathVariable Long cityId) {
        cityService.deleteCity(cityId);
        return ApiResTemplate.successResponse(SuccessCode.CITY_DELETE_SUCCESS, SuccessCode.CITY_DELETE_SUCCESS.getMessage());
    }

}
