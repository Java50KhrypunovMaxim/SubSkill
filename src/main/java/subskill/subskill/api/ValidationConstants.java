package subskill.subskill.api;

	public interface ValidationConstants {
		long MIN_PERSON_ID_VALUE = 100000l;
		long MAX_PERSON_ID_VALUE = 999999l;
		String WRONG_MIN_PERSON_ID_VALUE = "Person ID must be greater or equal " + MIN_PERSON_ID_VALUE;
		String WRONG_MAX_PERSON_ID_VALUE = "Person ID must be less or equal " + MAX_PERSON_ID_VALUE;
		String MISSING_PERSON_NAME_MESSAGE = "Missing person name";
		String MISSING_BIRTH_DATE_MESSAGE = "Missing person's birth date";
		String BIRTH_DATE_REGEXP = "\\d{4}-\\d{2}-\\d{2}";
		String PASSWORD_REGEXP = "^(?=.*[0-9])(?=.*[A-Z]).{6,15}$";
		String EMAIL_REGEXP = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
		String WRONG_DATE_FORMAT = "Wrong date format, must be YYYY-MM-dd";
		String MISSING_PERSON_EMAIL = "Missing email address";
		String WRONG_EMAIL_FORMAT = "Wrong email format";
		String MISSING_PERSON_USERNAME_MESSAGE = "Missing person user name";
		String MISSING_PASSWORD_MESSAGE = "Missing password";
		String WRONG_PASSWORD_CREATION_MESSAGE = "The password must consist of at least 6 characters. Contain a number and one capital letter";
		String MISSING_STATUS_MESSAGE = "Missing status of user";
		String MISSING_IMAGEURL_MESSAGE =  "Missing image URL";
		String MISSING_ROLE_MESSAGE = "Missing role of user";
		String MISSING_NICKNAME_MESSAGE = "Missing nickname of user";
	}

