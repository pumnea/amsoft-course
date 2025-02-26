package com.amsoftacademy.touristsheet.adapter.in.rest.mapper;

import com.amsoftacademy.touristsheet.adapter.in.rest.dto.TouristSheetResponse;
import com.amsoftacademy.touristsheet.domain.TouristSheet;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CustomerResponseMapper.class})
public interface TouristSheetResponseMapper {
    TouristSheet toEntity(TouristSheetResponse touristSheetResponse);

    TouristSheetResponse toDto(TouristSheet touristSheet);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TouristSheet partialUpdate(TouristSheetResponse touristSheetResponse, @MappingTarget TouristSheet touristSheet);
}