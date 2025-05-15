package org.unrealfff.data;

import org.unrealfff.commands.ExecuteScript;
import org.unrealfff.data.unit.Response;
import org.unrealfff.data.unit.Route;
import org.unrealfff.data.unit.SortRouteByDistance;
import org.unrealfff.io.Router;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.stream.Stream;

public class CollectionManager {
    /**
     * collectionManager controls collection
     */
    private LinkedHashSet<Route> collection;
    private FormatWorker fw;
    private String initTine;

    public CollectionManager (Router router, String args) {
        this.fw = new FormatWorker(args, router);
        this.initializeCollection();
        this.sort();
        this.initTine = this.fw.getCreationTime();
    }

    private void initializeCollection() {
        this.collection = this.fw.getFromFile();
    }

    public Response add(Route data) {
        this.collection.add(data);
        return new Response("added an element");
    }

    public Response removeById(Integer id) {
        Iterator<Route> iter = this.collection.iterator();
        boolean removed = false;
        while(iter.hasNext()) {
            Route i = iter.next();
            if (id.equals(i.getId())) {
                this.collection.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
            return new Response("removed an element with id " + id);
        }
        else {

            return new Response("element with this id does not exists");
        }
    }

    public Integer size() {
        return this.collection.size();
    }

    public Response contains(Route data) {
        return new Response(String.valueOf(this.collection.contains(data)));
    }

    public Response clear() {
        this.collection.clear();
        return new Response("cleared the collection");
    }

    public Response save() {
        return this.fw.save(this.getCollection());
    }
    public Response update(Route route, Integer id) {
        Iterator<Route> iter = this.collection.iterator();
        boolean met = false;
        while(iter.hasNext()) {
            Route i = iter.next();
            if (id.equals(i.getId())) {
                i.update(route);
                met = true;
                break;
            }
        }
        if (met) {
            return new Response("updated an element with id " + id);
        }
        else {

            return new Response("element with this id does not exists");
        }
    }
    public LinkedHashSet<Route> getCollection() {
        return new LinkedHashSet<>(this.collection);
    }

    public Response executeScript(String filename) {
        return this.fw.executeScript(filename);
    }

    public String getInitTime() {
        return this.initTine;
    }
    public void sort() {
        LinkedList<Route> routeList = new LinkedList<>(this.collection);
        routeList.sort(new SortRouteByDistance());
        this.collection = new LinkedHashSet<>(routeList);
    }

    public String getTypeOfCollection() {
        return this.collection.getClass().getSimpleName();
    }
}