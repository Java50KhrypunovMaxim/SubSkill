package com.subskill.dto;

import static com.subskill.api.ValidationConstants.*;

import java.util.Objects;

import com.subskill.models.MicroSkill;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ArticleDto (
		@NotEmpty (message=MISSING_ARTICLE_NAME_MESSAGE)
		String articleName,
		
		@NotEmpty (message= MISSING_TEXT_OF_ARTICLE_MESSAGE)
		String textOfArticle,

		@NotNull(message = MISSING_ID_OF_SKILLS)
		MicroSkill idOfSkills)
	
	{
		@Override
		public int hashCode() {
			return Objects.hash(articleName);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ArticleDto  other = (ArticleDto) obj;
			return Objects.equals(articleName, other.articleName);
		}


	}