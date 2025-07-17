package com.example.subscription_service.service;

import com.example.subscription_service.dto.TagRequestDto;
import com.example.subscription_service.dto.TagResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITagService {

    Page<TagResponseDto> getTags(Pageable pageable);
    TagResponseDto createTag(TagRequestDto tagRequestDto);
    TagResponseDto updateTag(String Id, TagRequestDto tagRequestDto);
    void deleteTag(String id);
}
