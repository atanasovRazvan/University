package quizzes.domain;

import quizzes.domain.Difficulty;
import quizzes.domain.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizValidator {

    public List<String> validate (Quiz quiz){

        List<String> errors = new ArrayList<String>();
        if (quiz.getNoQuestions()<=0) errors.add("No. of questions field: [negative no. of questions]\n");
        if (quiz.getNoQuestions()>100) errors.add("No. of questions field: [overflow no. of questions]\n");
        if (quiz.getCorrectAnswers()<0) errors.add("No. of correct answers field: [negative no. of correct answers]\n");
        if (quiz.getCorrectAnswers()>30) errors.add("No. of correct answers field: [overflow no. of correct answers]\n");
        if (quiz.getCorrectAnswers()>quiz.getNoQuestions()) errors.add("No. of correct answers field: [no. of correct answers > no. of questions]\n");
        if ((quiz.getDifficulty().compareTo(Difficulty.Easy)!=0) &&
            (quiz.getDifficulty().compareTo(Difficulty.Medium)!=0) &&
            (quiz.getDifficulty().compareTo(Difficulty.Hard)!=0))
            errors.add("Difficulty field: [invalid difficulty not in {easy, medium, hard}]\n");
        return errors;
    }
}
