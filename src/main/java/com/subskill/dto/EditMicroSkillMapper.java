package com.subskill.dto;

import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import com.subskill.models.MicroSkill;
import com.subskill.models.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface EditMicroSkillMapper {

    EditMicroSkillMapper INSTANCE = Mappers.getMapper(EditMicroSkillMapper.class);



    @Mapping(source = "microSkillDto.microSkillName", target = "name")
    @Mapping(source = "microSkillDto.microSkillRating", target = "rating")
    @Mapping(source = "microSkillDto.microSkillPhoto", target = "photo")
    @Mapping(source = "microSkillDto.microSkillDescription", target = "description")
    @Mapping(source = "microSkillDto.microSkillLevel", target = "level")
    @Mapping(source = "microSkillDto.microSkillTags", target = "tags")
    @Mapping(source = "microSkillDto.microSkillLearningTime", target = "learningTime")
    MicroSkill microSkillDtoToMicroSkill(MicroSkillDto microSkillDto);

    ProductMicroSkillDto microSkillToDto(MicroSkill microSkill);

    @Mapping(source = "productMicroSkillDto.microSkillName", target = "name")
    @Mapping(source = "productMicroSkillDto.microSkillRating", target = "rating")
    @Mapping(source = "productMicroSkillDto.microSkillPhoto", target = "photo")
    @Mapping(source = "productMicroSkillDto.microSkillDescription", target = "description")
    @Mapping(source = "productMicroSkillDto.microSkillLevel", target = "level")
    @Mapping(source = "productMicroSkillDto.microSkillTags", target = "tags")
    @Mapping(source = "productMicroSkillDto.microSkillLearningTime", target = "learningTime")
    void updateMicroSkillFromDto(ProductMicroSkillDto productMicroSkillDto, @MappingTarget MicroSkill microSkill);
}
