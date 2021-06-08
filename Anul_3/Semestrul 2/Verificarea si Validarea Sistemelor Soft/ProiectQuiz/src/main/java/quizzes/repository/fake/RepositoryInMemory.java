package quizzes.repository.fake;


import quizzes.domain.Quiz;
import quizzes.domain.QuizValidator;
import quizzes.repository.IRepository;
import quizzes.repository.RepositoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class RepositoryInMemory implements IRepository {

	private Vector<Quiz> quizzes;
	private QuizValidator validator;

	public RepositoryInMemory(QuizValidator validator) throws RepositoryException {
		quizzes = new Vector<Quiz>();
		this.validator = validator;
		populate();
	}

    public void populate() throws RepositoryException
	{
        add(new Quiz("q1", 10, "Easy", 7));
        add(new Quiz("q2", 15, "Medium", 3));
        add(new Quiz("q3", 9, "Easy", 9));
        add(new Quiz("q4", 5, "Hard", 2));
        add(new Quiz("q5", 20, "Medium", 9));
	}

	@Override
	public void add(Quiz quiz) throws RepositoryException{
		List<String> errors = validator.validate(quiz);
		if (errors.size() == 0)
			quizzes.add(quiz);
		else throw new RepositoryException(errors.toString());
	}

	@Override
	public List<Quiz> getAll(){
		return new ArrayList<>(quizzes);
	}

    @Override
    public int size() {
        return quizzes.size();
    }
}
