package runners;

import java.time.LocalDateTime;

public class DelayTaskRunner extends AbstractTaskRunner{

    public DelayTaskRunner(TaskRunner taskRunner) {
        super(taskRunner);
    }

    @Override
    public void afterExecution(){

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Delayed time: " + LocalDateTime.now() + "\n");
    }
}