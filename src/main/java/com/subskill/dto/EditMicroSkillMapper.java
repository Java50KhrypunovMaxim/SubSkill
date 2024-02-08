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

    @Mapping(source = "name", target = "name")
    @Mapping(source = "rating", target = "rating")
    @Mapping(source = "photo", target = "photo")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "level", target = "level")
    @Mapping(source = "tags", target = "tags")
    @Mapping(source = "learningTime", target = "learningTime")
    MicroSkill microSkillToEditDto(ProductMicroSkillDto microSkillDto, @MappingTarget MicroSkill microSkill);

    ProductMicroSkillDto microSkillToDto(MicroSkill microSkill);
}

