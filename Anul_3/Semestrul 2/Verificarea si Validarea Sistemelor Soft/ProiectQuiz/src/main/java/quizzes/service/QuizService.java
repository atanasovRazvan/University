package quizzes.service;

import java.util.ArrayList;
import java.util.List;


import quizzes.domain.Quiz;
import quizzes.repository.IRepository;
import quizzes.repository.RepositoryException;
import quizzes.repository.file.FileRepository;


public class QuizService {

    private IRepository repository;

    public QuizService(IRepository repository) {
        this.repository = repository;
    }

    public QuizService() {

    }

    public void setRepository(IRepository repository) {
        this.repository = repository;
    }

    public void addQuiz(Quiz quiz) throws RepositoryException {
        repository.add(quiz);
    }

    public List<Quiz> allQuizzes() {
        return repository.getAll();
    }

    public int size() {
        return repository.size();
    }

    //BBT EP + BVA
    //input: the list of all quizzes
    //output: the number of quizzes that have the maximum no. of correct answers

    public int maxScoreQuizCounter() {
        int k, i, p;

        List<Quiz> quizList = allQuizzes();

        i = 0;
        k = 0;
        p = 0;

        while (i < quizList.size()) {
            if (quizList.get(i).getCorrectAnswers() > quizList.get(p).getCorrectAnswers()) {
                p = i;
                k = 1;
            } else if (quizList.get(p).getCorrectAnswers() == quizList.get(i).getCorrectAnswers())
                k++;
            i++;
        }
        if (quizList.size() == 0) return -1;
        return k;
    }

    public int maxScore(){
        int maxScore=0;
        List<Quiz> quizList = allQuizzes();

        for (int i = 0; i < quizList.size(); i++)
            if (quizList.get(i).getCorrectAnswers() > maxScore)
                maxScore = quizList.get(i).getCorrectAnswers();

        return maxScore;
    }

    public List<Quiz> maxScoreQuizList() {
        List<Quiz> quizList = allQuizzes();
        List<Quiz> maxScoreQuizList = new ArrayList<Quiz>();;
        int maxScore = maxScore();

        for (int i = 0; i < quizList.size(); i++)
            if (quizList.get(i).getCorrectAnswers() == maxScore)
                maxScoreQuizList.add(quizList.get(i));

        return maxScoreQuizList;
    }

}



