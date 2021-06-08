package quizzes;

import quizzes.repository.file.FileRepository;
import quizzes.service.QuizService;
import quizzes.domain.QuizValidator;
import quizzes.ui.QuizViewer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
       String fileName = "data/quizzes.txt";

        FileRepository repo = null;
        QuizValidator validator =new QuizValidator();
        try {
            repo = new FileRepository(fileName, validator);
        } catch (IOException e) {
            e.printStackTrace();
        }

        QuizService ctrl = new QuizService(repo);

       QuizViewer console = new QuizViewer(ctrl);
       console.run();
    }
}
