-- Code to initially create and populate the sql database

--Make the schema
DROP SCHEMA IF EXISTS survey CASCADE;
CREATE SCHEMA survey;

SET SCHEMA 'survey';

-------------------
-- Create Tables --
-------------------

CREATE TABLE surveys (
	id SERIAL PRIMARY KEY,
	title TEXT NOT NULL,
	description TEXT,
	date_created DATE NOT NULL,
	closing_date DATE,
	"template" BOOL NOT NULL,
	published BOOL NOT NULL
);

CREATE TABLE editors (
	id SERIAL PRIMARY KEY,
	email TEXT NOT NULL,
	survey_id INT REFERENCES surveys(id) ON DELETE RESTRICT
);

CREATE TABLE survey_history (
	id SERIAL PRIMARY KEY,
	survey_id INT REFERENCES surveys(id) ON DELETE RESTRICT,
	user_email TEXT NOT NULL,
	date_assigned DATE NOT NULL,
	date_completed DATE
);

CREATE TABLE question_type (
	id SERIAL PRIMARY KEY,
	question_type TEXT NOT NULL UNIQUE
);

CREATE TABLE questions (
	id SERIAL PRIMARY KEY,
	question TEXT NOT NULL,
	type_id INT REFERENCES question_type(id) ON DELETE RESTRICT
);

CREATE TABLE junction_survey_questions (
	id SERIAL PRIMARY KEY,
	survey_id INT REFERENCES surveys(id) ON DELETE RESTRICT,
	question_id INT REFERENCES questions(id) ON DELETE RESTRICT,
	question_order INT
);

CREATE TABLE answers (
	id SERIAL PRIMARY KEY,
	answer TEXT NOT NULL,
	question_id INT REFERENCES questions(id) ON DELETE RESTRICT
);

CREATE TABLE responses (
	id SERIAL PRIMARY KEY,
	user_email TEXT,
	survey_id INT REFERENCES surveys(id) ON DELETE RESTRICT,
	answer_id INT REFERENCES answers(id) ON DELETE RESTRICT
);

------------------
-- Create Views --
------------------
CREATE VIEW user_responses AS
SELECT
	sh.user_email AS "User",
	s.title AS "Title",
	s.description AS "Description",
	s.date_created AS "Survey Created",
	s.closing_date AS "Survey Closes",
	sh.date_assigned AS "Assigned to User",
	sh.date_completed AS "Completed",
	q.question AS "Question",
	a.answer AS "Response"
FROM surveys s
JOIN survey_history sh ON s.id = sh.survey_id
JOIN junction_survey_questions j ON s.id = j.survey_id
JOIN questions q ON  q.id = j.question_id
JOIN answers a ON q.id = a.question_id
JOIN responses r ON a.id = r.answer_id AND r.user_email = sh.user_email;

---------------------
-- Populate Tables --
---------------------
INSERT INTO question_type (question_type) VALUES ('Boolean'), ('Single Choice'), ('Multiple Answer'), ('Rating'), ('Open Ended'), ('Yes/No'), ('Agree/Disagree');

-- Insert Survey 1
INSERT INTO surveys (title, description, date_created, "template", published) VALUES ('QC Survey', 'A survey for training feedback.', '02-28-2019', TRUE, TRUE);
INSERT INTO questions (question, type_id) VALUES ('Name (Optional)', 5);
INSERT INTO questions (question, type_id) VALUES ('Email (Optional)', 5);
INSERT INTO questions (question, type_id) VALUES ('What was your most recently completed week of training?', 3);
INSERT INTO questions (question, type_id) VALUES ('How satisfied were you with the Training? ', 4);
INSERT INTO questions (question, type_id) VALUES ('Materials provided were helpful', 7);
INSERT INTO questions (question, type_id) VALUES ('Content/Project Planning was well organized', 7);
INSERT INTO questions (question, type_id) VALUES ('Questions were encouraged and answered', 7);
INSERT INTO questions (question, type_id) VALUES ('Training and Projects met my expectations', 7);
INSERT INTO questions (question, type_id) VALUES ('Have you started working on your portfolio?', 6);
INSERT INTO questions (question, type_id) VALUES ('Any additional comments regarding above ratings', 5);
INSERT INTO questions (question, type_id) VALUES ('Any overall feedback (Training / Trainer / QC)', 5);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (1, 1, 1);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (1, 2, 2);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (1, 3, 3);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (1, 4, 4);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (1, 5, 5);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (1, 6, 6);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (1, 7, 7);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (1, 8, 8);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (1, 9, 9);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (1, 10, 10);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (1, 11, 11);
INSERT INTO answers (answer, question_id) VALUES ('Week 1', 3);
INSERT INTO answers (answer, question_id) VALUES ('Week 2', 3);
INSERT INTO answers (answer, question_id) VALUES ('Week 3', 3);
INSERT INTO answers (answer, question_id) VALUES ('Week 4', 3);
INSERT INTO answers (answer, question_id) VALUES ('Week 5', 3);
INSERT INTO answers (answer, question_id) VALUES ('Week 6', 3);
INSERT INTO answers (answer, question_id) VALUES ('Week 7', 3);
INSERT INTO answers (answer, question_id) VALUES ('Week 8', 3);
INSERT INTO answers (answer, question_id) VALUES ('Week 9', 3);
INSERT INTO answers (answer, question_id) VALUES ('1', 4);
INSERT INTO answers (answer, question_id) VALUES ('2', 4);
INSERT INTO answers (answer, question_id) VALUES ('3', 4);
INSERT INTO answers (answer, question_id) VALUES ('4', 4);
INSERT INTO answers (answer, question_id) VALUES ('5', 4);
INSERT INTO answers (answer, question_id) VALUES ('Strongly Agree', 5);
INSERT INTO answers (answer, question_id) VALUES ('Agree', 5);
INSERT INTO answers (answer, question_id) VALUES ('Disagree', 5);
INSERT INTO answers (answer, question_id) VALUES ('Strongly Disagree', 5);
INSERT INTO answers (answer, question_id) VALUES ('N/A', 5);
INSERT INTO answers (answer, question_id) VALUES ('Strongly Agree', 6);
INSERT INTO answers (answer, question_id) VALUES ('Agree', 6);
INSERT INTO answers (answer, question_id) VALUES ('Disagree', 6);
INSERT INTO answers (answer, question_id) VALUES ('Strongly Disagree', 6);
INSERT INTO answers (answer, question_id) VALUES ('N/A', 6);
INSERT INTO answers (answer, question_id) VALUES ('Strongly Agree', 7);
INSERT INTO answers (answer, question_id) VALUES ('Agree', 7);
INSERT INTO answers (answer, question_id) VALUES ('Disagree', 7);
INSERT INTO answers (answer, question_id) VALUES ('Strongly Disagree', 7);
INSERT INTO answers (answer, question_id) VALUES ('N/A', 6);
INSERT INTO answers (answer, question_id) VALUES ('Strongly Agree', 8);
INSERT INTO answers (answer, question_id) VALUES ('Agree', 8);
INSERT INTO answers (answer, question_id) VALUES ('Disagree', 8);
INSERT INTO answers (answer, question_id) VALUES ('Strongly Disagree', 8);
INSERT INTO answers (answer, question_id) VALUES ('N/A', 8);
INSERT INTO answers (answer, question_id) VALUES ('Yes', 9);
INSERT INTO answers (answer, question_id) VALUES ('No', 9);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 7, 'srtucker28@icloud.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 13, 'srtucker28@icloud.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 16, 'srtucker28@icloud.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 21, 'srtucker28@icloud.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 26, 'srtucker28@icloud.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 31, 'srtucker28@icloud.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 35, 'srtucker28@icloud.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 8, 'hankzimmer7@gmail.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 14, 'hankzimmer7@gmail.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 15, 'hankzimmer7@gmail.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 20, 'hankzimmer7@gmail.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 26, 'hankzimmer7@gmail.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 30, 'hankzimmer7@gmail.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 35, 'hankzimmer7@gmail.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 7, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 13, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 16, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 21, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 26, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 31, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 35, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 8, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 14, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 15, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 20, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 26, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 30, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 35, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 4, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 16, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 17, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 23, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 29, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 32, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (1, 35, null);

-- Insert Survey 2
INSERT INTO surveys (title, description, date_created, "template", published) VALUES ('Staging Manager Evaluation', 'A survey for associates in staging to evaluate the stagin manager.', '03-07-2019', TRUE, TRUE);
INSERT INTO questions (question, type_id) VALUES ('Name (Optional)', 5);
INSERT INTO questions (question, type_id) VALUES ('Email (Optional)', 5);
INSERT INTO questions (question, type_id) VALUES ('How many weeks have you been in staging?', 3);
INSERT INTO questions (question, type_id) VALUES ('My time in staging been productive.', 6);
INSERT INTO questions (question, type_id) VALUES ('How well has the staging manager helped you with being prepared for interviews?', 4);
INSERT INTO questions (question, type_id) VALUES ('Any additional comments regarding above ratings?', 5);
INSERT INTO questions (question, type_id) VALUES ('Any overall feedback?', 5);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (2, 12, 1);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (2, 13, 2);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (2, 14, 3);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (2, 15, 4);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (2, 16, 5);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (2, 17, 6);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (2, 18, 7);
INSERT INTO answers (answer, question_id) VALUES ('<1 Week', 14);
INSERT INTO answers (answer, question_id) VALUES ('1 Week', 14);
INSERT INTO answers (answer, question_id) VALUES ('2 Weeks', 14);
INSERT INTO answers (answer, question_id) VALUES ('3 Weeks', 14);
INSERT INTO answers (answer, question_id) VALUES ('4 Weeks', 14);
INSERT INTO answers (answer, question_id) VALUES ('5 Weeks', 14);
INSERT INTO answers (answer, question_id) VALUES ('6 Weeks', 14);
INSERT INTO answers (answer, question_id) VALUES ('7 Weeks', 14);
INSERT INTO answers (answer, question_id) VALUES ('8 Weeks', 14);
INSERT INTO answers (answer, question_id) VALUES ('9 Weeks', 14);
INSERT INTO answers (answer, question_id) VALUES ('10 Weeks', 14);
INSERT INTO answers (answer, question_id) VALUES ('>10 Weeks', 14);
INSERT INTO answers (answer, question_id) VALUES ('Strongly Agree', 15);
INSERT INTO answers (answer, question_id) VALUES ('Agree', 15);
INSERT INTO answers (answer, question_id) VALUES ('Disagree', 15);
INSERT INTO answers (answer, question_id) VALUES ('Strongly Disagree', 15);
INSERT INTO answers (answer, question_id) VALUES ('N/A', 15);
INSERT INTO answers (answer, question_id) VALUES ('1', 16);
INSERT INTO answers (answer, question_id) VALUES ('2', 16);
INSERT INTO answers (answer, question_id) VALUES ('3', 16);
INSERT INTO answers (answer, question_id) VALUES ('4', 16);
INSERT INTO answers (answer, question_id) VALUES ('5', 16);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (2, 38, 'imandavisy@gmail.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (2, 50, 'imandavisy@gmail.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (2, 58, 'imandavisy@gmail.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (2, 39, 'dunieskior@gmail.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (2, 51, 'dunieskior@gmail.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (2, 54, 'dunieskior@gmail.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (2, 43, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (2, 51, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (2, 57, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (2, 42, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (2, 49, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (2, 56, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (2, 39, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (2, 48, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (2, 55, null);

--Insert Survey 3
INSERT INTO surveys (title, description, date_created, "template", published) VALUES ('Check In Survey', 'A survey for associates in virtual staging to check in.', '03-11-2019', TRUE, TRUE);
INSERT INTO questions (question, type_id) VALUES ('How long do you have until you begin work at your assigned location?', 5);
INSERT INTO questions (question, type_id) VALUES ('Are you prepared to start work at your work location?', 6);
INSERT INTO questions (question, type_id) VALUES ('How smooth was the transition to virtual staging?', 4);
INSERT INTO questions (question, type_id) VALUES ('Any additional comments regarding above ratings?', 5);
INSERT INTO questions (question, type_id) VALUES ('Any overall feedback?', 5);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (3, 19, 1);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (3, 20, 2);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (3, 21, 3);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (3, 22, 4);
INSERT INTO junction_survey_questions (survey_id, question_id, question_order) VALUES (3, 23, 5);
INSERT INTO answers (answer, question_id) VALUES ('Yes', 20);
INSERT INTO answers (answer, question_id) VALUES ('No', 20);
INSERT INTO answers (answer, question_id) VALUES ('1', 21);
INSERT INTO answers (answer, question_id) VALUES ('2', 21);
INSERT INTO answers (answer, question_id) VALUES ('3', 21);
INSERT INTO answers (answer, question_id) VALUES ('4', 21);
INSERT INTO answers (answer, question_id) VALUES ('5', 21);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (3, 59, 'loricodes@gmail.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (3, 64, 'loricodes@gmail.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (3, 60, 'srtucker28@icloud.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (3, 65, 'srtucker28@icloud.com');
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (3, 59, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (3, 63, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (3, 60, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (3, 64, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (3, 59, null);
INSERT INTO responses (survey_id, answer_id, user_email) VALUES (3, 65, null);

-- Add users as editors
INSERT INTO editors (email, survey_id) VALUES ('loricodes@gmail.com', 1);
INSERT INTO editors (email, survey_id) VALUES ('imandavisy@gmail.com', 1);
INSERT INTO editors (email, survey_id) VALUES ('goncalvesjohnp@gmail.com', 1);
INSERT INTO editors (email, survey_id) VALUES ('srtucker28@icloud.com', 1);
INSERT INTO editors (email, survey_id) VALUES ('hankzimmer7@gmail.com', 1);
INSERT INTO editors (email, survey_id) VALUES ('dunieskior@gmail.com', 1);
INSERT INTO editors (email, survey_id) VALUES ('kyserrecchia@gmail.com', 1);
INSERT INTO editors (email, survey_id) VALUES ('loricodes@gmail.com', 2);
INSERT INTO editors (email, survey_id) VALUES ('imandavisy@gmail.com', 2);
INSERT INTO editors (email, survey_id) VALUES ('goncalvesjohnp@gmail.com', 2);
INSERT INTO editors (email, survey_id) VALUES ('srtucker28@icloud.com', 2);
INSERT INTO editors (email, survey_id) VALUES ('hankzimmer7@gmail.com', 2);
INSERT INTO editors (email, survey_id) VALUES ('dunieskior@gmail.com', 2);
INSERT INTO editors (email, survey_id) VALUES ('kyserrecchia@gmail.com', 2);
INSERT INTO editors (email, survey_id) VALUES ('loricodes@gmail.com', 3);
INSERT INTO editors (email, survey_id) VALUES ('imandavisy@gmail.com', 3);
INSERT INTO editors (email, survey_id) VALUES ('goncalvesjohnp@gmail.com', 3);
INSERT INTO editors (email, survey_id) VALUES ('srtucker28@icloud.com', 3);
INSERT INTO editors (email, survey_id) VALUES ('hankzimmer7@gmail.com', 3);
INSERT INTO editors (email, survey_id) VALUES ('dunieskior@gmail.com', 3);
INSERT INTO editors (email, survey_id) VALUES ('kyserrecchia@gmail.com', 3);

-- Assign users to surveys
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (1, 'loricodes@gmail.com', '03-01-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (1, 'imandavisy@gmail.com', '03-01-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (1, 'goncalvesjohnp@gmail.com', '03-01-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (1, 'srtucker28@icloud.com', '03-01-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (1, 'hankzimmer7@gmail.com', '03-01-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (1, 'dunieskior@gmail.com', '03-01-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (1, 'kyserrecchia@gmail.com', '03-01-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (2, 'loricodes@gmail.com', '03-09-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (2, 'imandavisy@gmail.com', '03-09-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (2, 'goncalvesjohnp@gmail.com', '03-09-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (2, 'srtucker28@icloud.com', '03-09-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (2, 'hankzimmer7@gmail.com', '03-09-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (2, 'dunieskior@gmail.com', '03-09-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (2, 'kyserrecchia@gmail.com', '03-09-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (3, 'loricodes@gmail.com', '03-11-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (3, 'imandavisy@gmail.com', '03-11-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (3, 'goncalvesjohnp@gmail.com', '03-11-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (3, 'srtucker28@icloud.com', '03-11-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (3, 'hankzimmer7@gmail.com', '03-11-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (3, 'dunieskior@gmail.com', '03-11-2019', null);
INSERT INTO survey_history (survey_id, user_email, date_assigned, date_completed) VALUES (3, 'kyserrecchia@gmail.com', '03-11-2019', null);