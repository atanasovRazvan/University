package ubb.scs.map.ir.sem1.runner;

import ubb.scs.map.ir.sem1.utils.Constants;

import java.time.LocalDateTime;

public class DelayTaskRunner extends AbstractTaskRunner {
    public DelayTaskRunner(TaskRunner runner) {
        super(runner);
    }

    @Override
    public void executeOneTask() {
        try {
            Thread.sleep(2000);
            super.executeOneTask();
            System.out.println("TASK EXECUTAT LA: " + LocalDateTime.now().format(Constants.DATE_TIME_FORMATER));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
