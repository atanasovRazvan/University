package quizzes.ui;

import java.util.List;
import java.util.Scanner;

import quizzes.domain.Quiz;

import quizzes.repository.RepositoryException;
import quizzes.service.QuizService;

public class QuizViewer {
	public QuizService service;
	Scanner in;
	
	public QuizViewer(QuizService service)
	{
		this.service = service;
		this.in = new Scanner(System.in);
	}
	
	public QuizService getService(){
		return this.service;
	}
	
	public Scanner getIn(){
		return this.in;
	}
	
	public void setService(QuizService service){
		this.service =service;
	}
	
	public void setIn(Scanner newIn){
		this.in=newIn;
	}
	
	public void printMenu()
	{
		String menu;
		menu="Quiz Management Menu: \n";
		menu +="\t 0 - exit \n";
		menu +="\t 1 - save a new quiz; \n";
		menu +="\t 2 - list all quizzes; \n";
		menu +="\t 3 - list number of quizzes with the highest number of correct answers; \n";
		menu +="\t 4 - list the quizzes with the highest number of correct answers; \n";

		System.out.println(menu);
	}
	
	
	
	
	public void run()
	{
		printMenu();
		int cmd=in.nextInt();
		in.nextLine();	
		while(cmd!=0)
		{
			if(cmd==1)
			{
				Quiz quiz = null;
				System.out.println("Enter id:");
				String id = in.nextLine();				
				System.out.println("Enter no of questions:");
				int noQuestions = Integer.parseInt(in.nextLine());
				System.out.println("Enter difficulty:");
				String difficulty = in.nextLine();
				System.out.println("Enter no of correct answers:");
				int correntAnswers =  Integer.parseInt(in.nextLine());
				quiz = new Quiz(id, noQuestions, difficulty, correntAnswers);
				try {
					service.addQuiz(quiz);
				} catch (RepositoryException e) {
					System.out.println(e.getMessage());
				}
			}
			if(cmd==2){
				System.out.println("The quizzes list is:");
				service.allQuizzes().forEach(System.out::println);
			}

			if(cmd==3){
				System.out.println("The number of quizzes with the highest score is: " + service.maxScoreQuizCounter());
			}

			if(cmd==4){
				System.out.println("The quizzes list with the highest score is:");
				service.maxScoreQuizList().forEach(System.out::println);
			}

			printMenu();
			cmd=in.nextInt();
			in.nextLine();
		}
	}
}

