package com.example.subscription_service.mapper;


import com.example.subscription_service.dto.PlanRequestDto;
import com.example.subscription_service.dto.PlanResponseDto;
import com.example.subscription_service.model.Plan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlanMapper {

   Plan toModel(PlanRequestDto planRequestDto);

   PlanResponseDto toDto(Plan plan);

}
