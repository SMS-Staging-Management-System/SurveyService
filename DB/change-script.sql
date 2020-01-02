ALTER TABLE survey_service.surveys
	ADD COLUMN creator TEXT,
	DROP COLUMN published;
