package com.amsoftacademy.touristsheet.adapter.in.rest.mapper;

import com.amsoftacademy.touristsheet.adapter.in.rest.dto.EmergencyContactDto;
import com.amsoftacademy.touristsheet.domain.EmergencyContact;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmergencyContactMapper {
    EmergencyContact toEntity(EmergencyContactDto emergencyContactDto);

    EmergencyContactDto toDto(EmergencyContact emergencyContact);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EmergencyContact partialUpdate(EmergencyContactDto emergencyContactDto, @MappingTarget EmergencyContact emergencyContact);
}