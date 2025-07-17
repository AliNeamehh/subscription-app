package com.example.subscription_service.service;


import com.example.subscription_service.dto.TagRequestDto;
import com.example.subscription_service.dto.TagResponseDto;
import com.example.subscription_service.exception.NameAlreadyExistsException;
import com.example.subscription_service.exception.TagNotFoundException;
import com.example.subscription_service.mapper.TagMapper;
import com.example.subscription_service.model.Tag;
import com.example.subscription_service.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService implements ITagService {

    private final TagRepository tagRepository;

    private final TagMapper tagMapper;

    public List<TagResponseDto> getTags() {

        List<Tag> tags = tagRepository.findAll();

        return tags.stream().map(tagMapper::toResponseDto).toList();

    }

    public TagResponseDto createTag(TagRequestDto tagRequestDto) {

        if (tagRepository.existsByName(tagRequestDto.getName())) {
            throw new NameAlreadyExistsException("Name already exists: " + tagRequestDto.getName());
        }

        Tag tag = tagRepository.save(tagMapper.toModel(tagRequestDto));

        return tagMapper.toResponseDto(tag);
    }


    public TagResponseDto updateTag(String Id, TagRequestDto tagRequestDto) {

        Tag tag = tagRepository.findById(Id).orElseThrow(() -> new TagNotFoundException("Tag is not found with this id: " + Id));

        tag.setName(tagRequestDto.getName());
        tag.setDescription(tagRequestDto.getDescription());

        Tag updatedTag = tagRepository.save(tag);

        return tagMapper.toResponseDto(updatedTag);

    }


    public void deleteTag(String id) {
        tagRepository.deleteById(id);
    }


}
