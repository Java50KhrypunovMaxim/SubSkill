package com.subskill.dto;

import com.subskill.models.MicroSkill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface EditMicroSkillMapper {

    EditMicroSkillMapper INSTANCE = Mappers.getMapper(EditMicroSkillMapper.class);

    @Mapping(source = "microSkillName", target = "name")
    @Mapping(source = "microSkillRating", target = "rating")
    @Mapping(source = "microSkillPhoto", target = "photo")
    @Mapping(source = "microSkillDescription", target = "description")
    @Mapping(source = "microSkillLevel", target = "level")
    @Mapping(source = "microSkillTags", target = "tags")
    @Mapping(source = "microSkillLearningTime", target = "learningTime")
    MicroSkill microSkillToEditDto(ProductMicroSkillDto microSkillDto, @MappingTarget MicroSkill microSkill);

    ProductMicroSkillDto microSkillToDto(MicroSkill microSkill);
}

