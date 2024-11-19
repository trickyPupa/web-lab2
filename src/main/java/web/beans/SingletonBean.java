package web.beans;

import jakarta.enterprise.context.ApplicationScoped;
import web.models.Point;

import java.util.*;

@ApplicationScoped
public class SingletonBean {
    private static final SingletonBean instance = new SingletonBean();

    private final TreeSet<Point> list = new TreeSet<>(Comparator.comparing(Point::getDatetime));

    private SingletonBean() {}

    public static SingletonBean getInstance() {
        return instance;
    }

    public void add(Point p) {
        list.add(p);
    }

    public ArrayList<Point> getList() {
        return new ArrayList<>(list);
    }
}
