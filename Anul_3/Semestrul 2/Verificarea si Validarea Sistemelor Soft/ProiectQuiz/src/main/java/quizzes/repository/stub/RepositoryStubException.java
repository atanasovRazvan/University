package quizzes.repository.stub;


import quizzes.domain.Quiz;
import quizzes.repository.IRepository;
import quizzes.repository.RepositoryException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepositoryStubException implements IRepository {

	private List<Quiz> quizzes;

	public RepositoryStubException() throws RepositoryException {
		quizzes = Arrays.asList(new Quiz("q1", 10, "Easy", 7),
				new Quiz("q2", 15, "Medium", 3),
				new Quiz("q3", 9, "Easy", 9));
	}

    @Override
	public void add(Quiz quiz) throws RepositoryException{
		//default behaviour - throws exception
		throw new RepositoryException("invalid quiz");
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
