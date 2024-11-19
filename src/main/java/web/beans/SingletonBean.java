package web.beans;

import jakarta.enterprise.context.ApplicationScoped;
import web.models.Point;

import java.util.ArrayList;

@ApplicationScoped
public class SingletonBean {
    private static final SingletonBean instance = new SingletonBean();

    private final ArrayList<Point> list = new ArrayList<>();

    private SingletonBean() {}

    public static SingletonBean getInstance() {
        return instance;
    }

    public void add(Point p) {
        list.add(p);
    }

    public ArrayList<Point> getList() {
        return list;
    }
}
