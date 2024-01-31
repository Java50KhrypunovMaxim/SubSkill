package com.subskill.dto;

import com.subskill.models.MicroSkill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EditMicroSkillMapper {

    EditMicroSkillMapper INSTANCE = Mappers.getMapper(EditMicroSkillMapper.class);

    @Mapping(source = "microSkillName", target = "name")
    @Mapping(source = "microSkillRating", target = "rating")
    @Mapping(source = "microSkillPhoto", target = "photo")
    @Mapping(source = "microSkillDescription", target = "description")
    @Mapping(source = "microSkillLevel", target = "level")
    @Mapping(source = "microSkillTags", target = "tags")
    @Mapping(source = "microSkillLearningTime", target = "learningTime")
    MicroSkill microSkillToEditDto(MicroSkill microSkill, ProductMicroSkillDto productMicroSkillDto);

    @Mapping(source = "microSkillViews", target = "views")
    ProductMicroSkillDto microSkillEditDtoViews(MicroSkill microSkill);

    ProductMicroSkillDto microSkillToDto(MicroSkill microSkill);

    MicroSkill dtoToMicroSkill(ProductMicroSkillDto productMicroSkillDto);
}