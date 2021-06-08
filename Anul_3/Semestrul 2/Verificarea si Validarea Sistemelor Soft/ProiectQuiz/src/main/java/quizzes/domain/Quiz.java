package quizzes.domain;

public class Quiz {
	private String id;
	private int noQuestions;
	private Difficulty difficulty;
	private int correctAnswers;


	public Quiz() {
		id = "Quiz";
		noQuestions = 0;
		difficulty = Difficulty.Easy;
		correctAnswers = 0;
	}
	
	public Quiz(String id, int noQ, String difficulty, int correctAnswers) {
		this.id = id;
		this.noQuestions = noQ;
		switch(difficulty) {
			case "Easy": this.difficulty = Difficulty.Easy; break;
			case "Medium": this.difficulty = Difficulty.Medium; break;
			case "Hard": this.difficulty = Difficulty.Hard; break;
			default: this.difficulty = Difficulty.None; break;
		}
		this.correctAnswers = correctAnswers;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNoQuestions() {
		return noQuestions;
	}

	public void setNoQuestions(int noQuestions) {
		this.noQuestions = noQuestions;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public int getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(int correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	@Override
	public String toString() {
		return "Quiz{" +
				"id='" + id + '\'' +
				", noQuestions=" + noQuestions +
				", difficulty=" + difficulty +
				", correctAnswers=" + correctAnswers +
				'}';
	}
}
