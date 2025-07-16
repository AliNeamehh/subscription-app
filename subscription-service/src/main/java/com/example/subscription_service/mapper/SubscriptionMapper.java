package com.example.subscription_service.mapper;

import com.example.subscription_service.dto.SubscribeRequestDto;
import com.example.subscription_service.dto.SubscribeResponseDto;
import com.example.subscription_service.model.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    Subscription toModel(SubscribeRequestDto subscribeRequestDto);
    SubscribeResponseDto toResponseDto(Subscription subscription);


}
