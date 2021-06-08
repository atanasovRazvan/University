import java.util.ArrayList;
import java.util.List;

public class MyRepository {

    private static MyRepository instance = null;
    private List<MyObject> objects;

    public static MyRepository getInstance(){
        if(instance == null)
            instance = new MyRepository();
        return instance;
    }

    private MyRepository(){
        this.objects = new ArrayList<>();
    }

    public void addObject(MyObject object){
        this.objects.add(object);
    }

    public MyObject[] findAll(){
        MyObject[] allObjects = new MyObject[10];
        int index = 0;
        for(MyObject obj : this.objects){
            allObjects[index] = obj;
            index++;
        }
        return allObjects;
    }

}
