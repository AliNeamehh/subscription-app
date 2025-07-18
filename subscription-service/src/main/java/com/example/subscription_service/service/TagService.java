package com.example.subscription_service.service;


import com.example.subscription_service.dto.TagRequestDto;
import com.example.subscription_service.dto.TagResponseDto;
import com.example.subscription_service.exception.NameAlreadyExistsException;
import com.example.subscription_service.exception.NotFoundException;
import com.example.subscription_service.mapper.TagMapper;
import com.example.subscription_service.model.Plan;
import com.example.subscription_service.model.Tag;
import com.example.subscription_service.repository.PlanRepository;
import com.example.subscription_service.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TagService implements ITagService {

    private final TagRepository tagRepository;

    private final PlanRepository planRepository;

    private final TagMapper tagMapper;

    public Page<TagResponseDto> getTags(Pageable pageable) {
        Page<Tag> tagPage = tagRepository.findAll(pageable);
        return tagPage.map(tagMapper::toResponseDto);
    }


    public TagResponseDto createTag(TagRequestDto tagRequestDto) {
        if (tagRepository.existsByName(tagRequestDto.getName())) {
            throw new NameAlreadyExistsException("Name already exists: " + tagRequestDto.getName());
        }
        Tag tag = tagRepository.save(tagMapper.toModel(tagRequestDto));
        return tagMapper.toResponseDto(tag);
    }


    public TagResponseDto updateTag(String Id, TagRequestDto tagRequestDto) {

        Tag tag = tagRepository.findById(Id).orElseThrow(() -> new NotFoundException("Tag is not found with this id: " + Id));
        tag.setName(tagRequestDto.getName());
        tag.setDescription(tagRequestDto.getDescription());
        Tag updatedTag = tagRepository.save(tag);
        return tagMapper.toResponseDto(updatedTag);
    }

    public void deleteTag(String id) {
        tagRepository.deleteById(id);
    }


    public void validateTag(List<String> tagIds) {
        for (String tagId : tagIds) {
            tagRepository.findById(tagId).orElseThrow(() ->
                    new NotFoundException("Tag is not found with this id: " + tagId));
        }

    }


    public List<String> getMergedTagIds(String basePlanId, String inheritedPlanId) {

        Optional<Plan> optionalBasePlan = planRepository.findById(basePlanId);
        Optional<Plan> optionalInheritPlan = planRepository.findById(inheritedPlanId);

        if (optionalBasePlan.isEmpty()) {
            throw new NotFoundException("Base plan not found");
        }

        if (optionalInheritPlan.isEmpty()) {
            throw new NotFoundException("Inherited plan not found");
        }

        Plan basePlan = optionalBasePlan.get();
        Plan inheritedPlan = optionalInheritPlan.get();

        Set<String> mergedTagIds = new HashSet<>(basePlan.getTagIds());
        mergedTagIds.addAll(inheritedPlan.getTagIds());


        return new ArrayList<>(mergedTagIds);

    }
}
