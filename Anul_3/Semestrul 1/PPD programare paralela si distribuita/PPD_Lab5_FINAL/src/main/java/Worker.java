public class Worker extends Thread {

    private MyLinkedList list;
    private MyQueue<Monom> queue;

    public Worker(MyLinkedList list, MyQueue<Monom> queue){
        this.list = list;
        this.queue = queue;
    }

    @Override
    public void run(){
        try{
            Monom monom = queue.pop();
            while(monom != null){
                list.add(monom);
                monom = queue.pop();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
