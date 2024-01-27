package com.subskill.dto;

import com.subskill.models.MicroSkill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EditMicroSkillMapper {

    EditMicroSkillMapper INSTANCE = Mappers.getMapper(EditMicroSkillMapper.class );

    @Mapping(source = "microSkillname", target = "name")
    @Mapping(source = "microSkillrating", target = "rating")
    @Mapping(source = "microSkillphoto", target = "photo")
    @Mapping(source = "microSkillDescription", target = "description")
    @Mapping(source = "microSkillLevel", target = "level")
    @Mapping(source = "microSkillTags", target = "tags")
    @Mapping(source = "microSkillLearningtime", target = "learningTime")
    MicroSkill microSkillToEditDto(MicroSkill existingMicroSkill, EditMicroSkillDto editMicroSkillDto);


    EditMicroSkillDto microSkillToDto(MicroSkill microSkill);
}
