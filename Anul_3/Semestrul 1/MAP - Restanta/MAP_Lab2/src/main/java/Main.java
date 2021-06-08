import domain.MessageTask;
import domain.Task;
import factory.SortingTaskFactory;
import factory.Strategy;
import runners.DelayTaskRunner;
import runners.PrinterTaskRunner;
import runners.StrategyTaskRunner;
import sorters.SortingStrategy;

import java.time.LocalDateTime;

public class Main {


    public static MessageTask[] createMessageTaskArray(){
        MessageTask t1=new MessageTask("1","Feedback lab1",
                "Ai obtinut 9.60","Gigi", "Ana", LocalDateTime.now());
        MessageTask t2=new MessageTask("2","Feedback lab1",
                "Ai obtinut 5.60","Gigi", "Ana", LocalDateTime.now());
        MessageTask t3=new MessageTask("3","Feedback lab3",
                "Ai obtinut 10","Gigi", "Ana", LocalDateTime.now());
        return new MessageTask[]{t1,t2,t3};
    }

    public static Integer[] createArray(){

        return new Integer[]{1, 5, 2, 3, 7, 10, 2};

    }

    public static void main(String[] args) {
        MessageTask[] v = createMessageTaskArray();
        //for (MessageTask el : v)
        //    System.out.println(el.toString());

        StrategyTaskRunner runner = new StrategyTaskRunner(Strategy.FIFO);
        runner.addTask(v[0]);
        runner.addTask(v[1]);
        runner.addTask(v[2]);

        Task bubbleSort = SortingTaskFactory.getInstance().createSorter("4", "bubbleSort",
                createArray(), SortingStrategy.BUBBLESORT);
        runner.addTask(bubbleSort);

        Task quickSort = SortingTaskFactory.getInstance().createSorter("5", "quickSort",
                createArray(), SortingStrategy.QUICKSORT);
        runner.addTask(quickSort);

        //PrinterTaskRunner printerTaskRunner = new PrinterTaskRunner(runner);
        DelayTaskRunner delayTaskRunner = new DelayTaskRunner(runner);
        //printerTaskRunner.executeAll();
        delayTaskRunner.executeAll();

    }

}
