package com.subskill.api;

public interface ValidationConstants {
    String PASSWORD_REGEXP = "^(?=.*[0-9])(?=.*[A-Z]).{6,15}$";
    String EMAIL_REGEXP = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
    String MISSING_PERSON_EMAIL = "Missing email address";
    String WRONG_EMAIL_FORMAT = "Wrong email format";
    String MISSING_PERSON_USERNAME_MESSAGE = "Missing person user name";
    String REGISTRATION_USER_NOT_FOUND = "Missing registration user ";
    String MISSING_PASSWORD_MESSAGE = "Missing password";
    String WRONG_PASSWORD_CREATION_MESSAGE = "The password must consist of at least 6 characters. Contain a number and one capital letter";
    String MISSING_STATUS_MESSAGE = "Missing status of user";
    String MISSING_IMAGE_URL_MESSAGE = "Missing image URL";
    String MISSING_ROLE_MESSAGE = "Missing role of user";
    String MISSING_NICKNAME_MESSAGE = "Missing nickname of user";
    String INVALID_INPUT_DATA = "Invalid input data";
    String USER_NOT_FOUND = "User not found";
    String PASSWORD_NOT_FOUND = "password not found";
    String CONFIRMED_PASSWORD_NOT_FOUND = "confirmed password not found";
    String TOKEN_NOT_FOUND = "token not found";
    String MISSING_ARTICLE_NAME_MESSAGE = "Missing name of article";
    String MISSING_ID_OF_ARTICLE = "Missing id of article";
    String MISSING_TEXT_OF_ARTICLE_MESSAGE = "Missing text of article";
    String MISSING_ID_OF_SKILLS = "Missing id of skills";
    String MISSING_ID_OF_REVIEW = "Missing id of review";
    String MISSING_MICROSKILL_NAME_MESSAGE = "Missing name of microskill";
    String MISSING_MICROSKILL_RATING_MESSAGE = "Missing rating in microsskill";
    String MISSING_MICROSKILL_PHOTO_MESSAGE = "Missing photo in microskill";
    String TECHNOLOGY_ID_MISSING = "Missing technology id";
    String TECHNOLOGY_MISSING = "Missing technology";
    String MISSING_MICROSKILL_DESCRIPTION_MESSAGE = "Missing destription ";
    String MISSING_MICROSKILL_LEVEL_MESSAGE = "Missing Level message";
    String MISSING_MICROSKILL_TAGS_MESSAGE = "Missing tag message";
    String MISSING_MICROSKILL_PRICE_MESSAGE = "Missing price message";
    String MISSING_MICROSKILL_LEARNING_TIME_MESSAGE = "Missing learning time message";
    String MISSING_MICROSKILL_VIEWS_MESSAGE = "Missing views  message";
    String MISSING_NAME_OF_ARTICLE = "Missing name of article";
    String MISSING_TEXT_REVIEW_MESSAGE = "Missing text of review";
    String MISSING_RATING_MESSAGE = "Missing rating of review";
    String MISSING_MICRO_SKILLS_MESSAGE = "Missing micro skill";
    String MISSING_USER_MESSAGE = "Missing User";
    String MISSING_MICROSKILL_CREATION_DATE = "Missing creation date for microSkill";
    String MISSING_MICROSKILL_ABOUT_SKILL = "Missing aboutSkill";
    String MISSING_MICROSKILL_LAST_UPDATE_TIME = "Missing last updated time";
    String MIN_RATING = "Rating should be at least 1";
    String MAX_RATING = "Rating should be at most 5";
    String PROFESSION_NAME_MISSING = "Missing profession name";
    String PROFESSION_ID_MISSING = "Missing profession id";
    String MISSING_TECHNOLOGY_NAME_MESSAGE = "Missing technology name";
    String MISSING_PROFESSION_MESSAGE = "Missing profession";
    String MISSING_MICROSKILLS_MESSAGE = "Missing microskill";
    String INTEREST_ID_MISSING = "Profile Interest id is missing";
    String INTEREST_NAME_NOT_FOUND = "Profile Interest name is missing";

    
}

