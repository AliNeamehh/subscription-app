package com.example.subscription_service.service;

import com.example.subscription_service.dto.TagRequestDto;
import com.example.subscription_service.dto.TagResponseDto;

import java.util.List;

public interface ITagService {

    List<TagResponseDto> getTags();
    TagResponseDto createTag(TagRequestDto tagRequestDto);
    TagResponseDto updateTag(String Id, TagRequestDto tagRequestDto);
    void deleteTag(String id);
}
