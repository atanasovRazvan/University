import ubb.scs.map.ir.sem1.containers.Strategy;
import ubb.scs.map.ir.sem1.model.MessageTask;
import ubb.scs.map.ir.sem1.model.SortingAlgorithms;
import ubb.scs.map.ir.sem1.model.SortingTask;
import ubb.scs.map.ir.sem1.model.Task;
import ubb.scs.map.ir.sem1.runner.DelayTaskRunner;
import ubb.scs.map.ir.sem1.runner.PrinterTaskRunner;
import ubb.scs.map.ir.sem1.runner.StrategyTaskRunner;
import ubb.scs.map.ir.sem1.runner.TaskRunner;
import java.time.LocalDateTime;

public class Main {

    public static Task[] createMessageTaskArray(){
        Task t1 = new MessageTask("1","Feedback lab1",
                "Ai obtinut 9.60","Gigi", "Ana", LocalDateTime.now());
        Task t2 = new MessageTask("2","Feedback lab2",
                "Ai obtinut 5.60","Gigi", "Ana", LocalDateTime.now());
        Task t3 = new MessageTask("3","Feedback lab3",
                "Ai obtinut 10","Gigi", "Ana", LocalDateTime.now());
        Task t4 = new MessageTask("3","Feedback lab4",
                "Ai obtinut 8.70","Gigi", "Ana", LocalDateTime.now());
        Task t5 = new MessageTask("3","Feedback lab5",
                "Ai obtinut 7.90","Gigi", "Ana", LocalDateTime.now());
        return new Task[]{t1, t2, t3, t4, t5};
    }

    public static void main(String[] args) {

        System.out.println("----------  MessageTask  ----------");
        Task[] tasks1 = createMessageTaskArray();
        for (Task t:tasks1) {
            System.out.println(t.toString());
        }

        System.out.println("----------  StrategyTaskRunner_" + args[0] +"  ----------");
        Task[] tasks2 = createMessageTaskArray();
        TaskRunner runner1 = new StrategyTaskRunner(Strategy.valueOf(args[0]));
        for (Task t:tasks2) {
            runner1.addTask(t);
        }
        runner1.executeAll();

        System.out.println("----------  PrinterTaskRunner_" + args[0] +"  ----------");
        Task[] tasks3 = createMessageTaskArray();
        TaskRunner runner2 = new PrinterTaskRunner(runner1);
        for (Task t:tasks3) {
            runner2.addTask(t);
        }
        runner2.executeAll();

        System.out.println("----------  DelayTaskRunner_" + args[0] +"  ----------");
        Task[] tasks4 = createMessageTaskArray();
        TaskRunner runner3 = new DelayTaskRunner(runner1);
        for (Task t:tasks4) {
            runner3.addTask(t);
        }
        runner3.executeAll();

        System.out.println("----------  SortingTask  ----------");
        int[] v={100,2,35,4,3,67,44,90};
        Task st=new SortingTask("1","Feedback", v, SortingAlgorithms.BubbleSort);
        st.execute();
        int[] v1={100,2,35,4,3,67,44,90};
        Task st1=new SortingTask("2","Feedback", v1, SortingAlgorithms.QuickSort);
        st1.execute();
    }
}
