package quizzes.repository.file;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import quizzes.domain.Difficulty;
import quizzes.domain.Quiz;
import quizzes.domain.QuizValidator;
import quizzes.repository.IRepository;
import quizzes.repository.RepositoryException;


public class FileRepository implements IRepository {

	private Vector<Quiz> quizzes;
	private String fileName;
	private QuizValidator validator;

	public FileRepository(String fileName, QuizValidator validator) throws IOException {
		this.fileName = fileName;
		quizzes = new Vector<Quiz>();
		this.validator = validator;
		loadData();
	}

	public FileRepository(QuizValidator validator) {
		quizzes = new Vector<Quiz>();
		this.validator = validator;
	}
	
	public void loadData() throws IOException
	{
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		while ((line = br.readLine()) != null) {
			String s[] = line.split(";");
			Difficulty d = null;
			Quiz quiz = new Quiz(s[0], Integer.parseInt(s[1]), s[2], Integer.parseInt(s[3]));
            try {
                add(quiz);
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
        }
		br.close();
	}

	public void saveData() throws IOException {
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
		//		new FileOutputStream(fileName)));
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

		if (quizzes.size() != 0)
			quizzes.forEach (
				(quiz) ->{
					try {
						bw.write(quiz.getId()+";");
						bw.write(quiz.getNoQuestions()+";");
						bw.write(quiz.getDifficulty()+";");
						bw.write(quiz.getCorrectAnswers()+"");
						bw.write("\r\n");
					} catch (IOException e) {
						e.printStackTrace();
					};
				});
		bw.close();
	}

	@Override
	public void add(Quiz quiz) throws RepositoryException {
		List<String> errors = validator.validate(quiz);
		if (errors.size() == 0) {
			quizzes.add(quiz);
		}else throw new RepositoryException(errors.toString());
	}

	public void save(Quiz quiz){

		try {
			add(quiz);
			saveData();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
            e.printStackTrace();
        }
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
