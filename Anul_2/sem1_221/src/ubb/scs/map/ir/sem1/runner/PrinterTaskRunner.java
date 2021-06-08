package ubb.scs.map.ir.sem1.runner;

import ubb.scs.map.ir.sem1.utils.Constants;

import java.time.LocalDateTime;

public class PrinterTaskRunner extends AbstractTaskRunner {
    public PrinterTaskRunner(TaskRunner runner) {
        super(runner);
    }

    @Override
    public void executeOneTask() {
        super.executeOneTask();
        System.out.println("TASK EXECUTAT LA: " + LocalDateTime.now().format(Constants.DATE_TIME_FORMATER));
    }
}
