package subskill.subskill.api;

	public interface ValidationConstants {
		
		long MIN_PERSON_ID_VALUE = 100000l;
		long MAX_PERSON_ID_VALUE = 999999l;
		String WRONG_MIN_PERSON_ID_VALUE = "Person ID must be greater or equal " + MIN_PERSON_ID_VALUE;
		String WRONG_MAX_PERSON_ID_VALUE = "Person ID must be less or equal " + MAX_PERSON_ID_VALUE;
		String MISSING_PERSON_NAME_MESSAGE = "Missing person name";
		String MISSING_BIRTH_DATE_MESSAGE = "Missing person's birth date";
		String BIRTH_DATE_REGEXP = "\\d{4}-\\d{2}-\\d{2}";
		String WRONG_DATE_FORMAT = "Wrong date format, must be YYYY-MM-dd";
		String MISSING_PERSON_EMAIL = "Missing email address";
		String WRONG_EMAIL_FORMAT = "Wrong email format";
		 
	}

