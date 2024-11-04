package web.beans;

import web.models.Point;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Objects;

public class ResultBean implements Serializable {

    private final ArrayDeque<Point> list = new ArrayDeque<>();

    public void add(Point p) {
        list.addFirst(p);
    }

    public Collection<Point> getList() {
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultBean that = (ResultBean) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(list);
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "list=" + list +
                '}';
    }
}
