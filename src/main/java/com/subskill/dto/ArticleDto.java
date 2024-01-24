package com.subskill.dto;

import static com.subskill.api.ValidationConstants.*;

import java.util.Objects;
import jakarta.validation.constraints.NotEmpty;

public record ArticleDto (
		@NotEmpty (message=MISSING_ARTICLE_NAME_MESSAGE)
		String articleName,
		
		@NotEmpty (message= MISSING_TEXT_OF_ARTICLE_MESSAGE)
		String textOfArticle,
		
		@NotEmpty (message=MISSING_ID_OF_SKILLS)
		long idOfSkills)
	
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