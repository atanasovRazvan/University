package socialnetwork.controllers;

import java.util.ArrayList;
import java.util.List;

public class Observable {

    private static List<Observer> observerList;
    private static Observable obs_instance;

    public Observable(){
        observerList = new ArrayList<>();
    }

    public static Observable getInstance(){
        if(obs_instance == null)
            obs_instance = new Observable();
        return obs_instance;
    }

    public static void addObserver(Observer observer){ observerList.add(observer); }

    public static void notifyall(){ observerList.forEach(Observer::loadData); }

}
