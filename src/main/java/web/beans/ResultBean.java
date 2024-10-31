package web.beans;

import web.models.Point;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Collection;

public class ResultBean implements Serializable {

    private final ArrayDeque<Point> list = new ArrayDeque<>();

    public void add(Point p) {
        list.addFirst(p);
    }

    public Collection<Point> getList() {
        return list;
    }
}
