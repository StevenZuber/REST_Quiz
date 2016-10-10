-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_username` VARCHAR(45) NOT NULL,
  `user_password` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quiz`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quiz` ;

CREATE TABLE IF NOT EXISTS `quiz` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `score`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `score` ;

CREATE TABLE IF NOT EXISTS `score` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `value` FLOAT NOT NULL,
  `user_id` INT NOT NULL,
  `quiz_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_score_user_idx` (`user_id` ASC),
  INDEX `fk_score_quiz1_idx` (`quiz_id` ASC),
  CONSTRAINT `fk_score_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_score_quiz1`
    FOREIGN KEY (`quiz_id`)
    REFERENCES `quiz` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `question` ;

CREATE TABLE IF NOT EXISTS `question` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quiz_id` INT NOT NULL,
  `questionText` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_question_quiz1_idx` (`quiz_id` ASC),
  CONSTRAINT `fk_question_quiz1`
    FOREIGN KEY (`quiz_id`)
    REFERENCES `quiz` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `answer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `answer` ;

CREATE TABLE IF NOT EXISTS `answer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question_id` INT NOT NULL,
  `answerText` VARCHAR(256) NOT NULL,
  `isCorrect` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_answer_question1_idx` (`question_id` ASC),
  CONSTRAINT `fk_answer_question1`
    FOREIGN KEY (`question_id`)
    REFERENCES `question` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `user` (`user_id`, `user_username`, `user_password`) VALUES (1, 'test', 'test');
INSERT INTO `user` (`user_id`, `user_username`, `user_password`) VALUES (2, 'steven', 'password');
INSERT INTO `user` (`user_id`, `user_username`, `user_password`) VALUES (3, 'test2', 'test2');
INSERT INTO `user` (`user_id`, `user_username`, `user_password`) VALUES (4, 'cole_frock', 'cooper');

COMMIT;


-- -----------------------------------------------------
-- Data for table `quiz`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `quiz` (`id`, `name`) VALUES (1, 'Quiz 1');
INSERT INTO `quiz` (`id`, `name`) VALUES (2, 'Trivia');
INSERT INTO `quiz` (`id`, `name`) VALUES (3, 'Sports');

COMMIT;


-- -----------------------------------------------------
-- Data for table `score`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `score` (`id`, `value`, `user_id`, `quiz_id`) VALUES (1, 75.3, 2, 1);
INSERT INTO `score` (`id`, `value`, `user_id`, `quiz_id`) VALUES (2, 88.8, 1, 1);
INSERT INTO `score` (`id`, `value`, `user_id`, `quiz_id`) VALUES (3, 50, 4, 2);
INSERT INTO `score` (`id`, `value`, `user_id`, `quiz_id`) VALUES (4, 33.3, 2, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `question`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `question` (`id`, `quiz_id`, `questionText`) VALUES (1, 1, 'What\'s my favorite color?');
INSERT INTO `question` (`id`, `quiz_id`, `questionText`) VALUES (2, 1, 'How many cars have I owned?');
INSERT INTO `question` (`id`, `quiz_id`, `questionText`) VALUES (3, 2, 'Diameter of earth in miles?');
INSERT INTO `question` (`id`, `quiz_id`, `questionText`) VALUES (4, 2, 'Number of planets in solar system?');
INSERT INTO `question` (`id`, `quiz_id`, `questionText`) VALUES (5, 2, 'Light minutes from here to Mars?');
INSERT INTO `question` (`id`, `quiz_id`, `questionText`) VALUES (6, 3, 'Who sportsed the hardest last year?');
INSERT INTO `question` (`id`, `quiz_id`, `questionText`) VALUES (7, 3, 'What\'s the best sport?');
INSERT INTO `question` (`id`, `quiz_id`, `questionText`) VALUES (8, 3, 'Which football team has won the most superbowls?');
INSERT INTO `question` (`id`, `quiz_id`, `questionText`) VALUES (9, 1, 'From where do I hail?');

COMMIT;


-- -----------------------------------------------------
-- Data for table `answer`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (1, 1, 'Green', true);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (2, 1, 'Blue', false);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (3, 1, 'Orange', false);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (4, 2, '5', true);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (5, 2, '1', false);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (6, 2, '3', false);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (7, 3, 'About 8000', true);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (8, 3, 'About 10000', false);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (9, 3, 'Roughly 6', false);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (10, 4, '9', false);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (11, 4, '8', true);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (12, 4, '10', false);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (13, 5, '20-90', true);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (14, 5, '50-120', false);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (15, 5, 'a year', false);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (16, 6, 'Payton Manning', true);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (17, 6, 'Magic Johnson', false);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (18, 6, 'Steve Nagel', false);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (19, 7, 'bowling', false);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (20, 7, 'football', false);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (21, 7, 'chess', true);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (22, 8, 'Pittsburg Steelers', true);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (23, 8, 'Denver Broncos', false);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (24, 8, 'Chicago Raiders', false);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (25, 9, 'Fort Collins', true);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (26, 9, 'Denver', false);
INSERT INTO `answer` (`id`, `question_id`, `answerText`, `isCorrect`) VALUES (27, 9, 'Space', false);

COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
