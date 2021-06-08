package runners;

import java.time.LocalDateTime;

public class PrinterTaskRunner extends AbstractTaskRunner{

    public PrinterTaskRunner(TaskRunner taskRunner) {
        super(taskRunner);
    }

    @Override
    public void afterExecution() {
        System.out.println("Time: " + LocalDateTime.now() + "\n");
    }
}
