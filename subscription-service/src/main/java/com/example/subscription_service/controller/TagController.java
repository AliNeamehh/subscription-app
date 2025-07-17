package com.example.subscription_service.controller;
import com.example.subscription_service.dto.TagRequestDto;
import com.example.subscription_service.dto.TagResponseDto;
import com.example.subscription_service.service.ITagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
@Tag(name = "Tag Management", description = "APIs for managing tags ")
public class TagController {

    private final ITagService iTagService;

    @GetMapping
    @Operation(summary = "Get all tags")
    public ResponseEntity<List<TagResponseDto>> getAllTags() {
        List<TagResponseDto> tags=iTagService.getTags();
        return ResponseEntity.ok(tags);
    }



    @PostMapping
    @Operation(summary = "Create a new tag")
    public ResponseEntity<TagResponseDto> createTag(@Validated @RequestBody TagRequestDto tagRequestDto) {

        TagResponseDto createdTag = iTagService.createTag(tagRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTag);


    }

    @PutMapping("/{id}")
    @Operation(summary = "update an existing tag")
    public ResponseEntity<TagResponseDto> updateTag(@PathVariable String id, @Validated @RequestBody TagRequestDto tagRequestDto) {
        TagResponseDto updatedTag = iTagService.updateTag(id, tagRequestDto);
        return ResponseEntity.ok().body(updatedTag);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a tag")
    public ResponseEntity<Void> deleteTag(@PathVariable String id) {
        iTagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }

}
