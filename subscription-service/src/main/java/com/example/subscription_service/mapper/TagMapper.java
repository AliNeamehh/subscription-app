package com.example.subscription_service.mapper;


import com.example.subscription_service.dto.TagRequestDto;
import com.example.subscription_service.dto.TagResponseDto;
import com.example.subscription_service.model.Tag;
import org.mapstruct.Mapper;
import org.springframework.data.mongodb.core.ReactiveAggregationOperation;

@Mapper(componentModel = "spring")
public interface TagMapper {

   Tag toModel(TagRequestDto tagRequestDto);

   TagResponseDto  toResponseDto(Tag tag);

}
