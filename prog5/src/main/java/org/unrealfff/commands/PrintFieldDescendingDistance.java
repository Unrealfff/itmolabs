package org.unrealfff.commands;

import org.unrealfff.data.CollectionManager;
import org.unrealfff.data.unit.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class PrintFieldDescendingDistance implements Operation {
    /**
     * print_field_descending_distance - prints all distances in descending order
     */
    CollectionManager collectionManager;

    public PrintFieldDescendingDistance(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }



    @Override
    public Response execute(Request request) {
        if (this.checkArgs(request.args())) {
            LinkedHashSet<Route> collection = this.collectionManager.getCollection();
            String ans = "";
            ArrayList<Route> sortable = new ArrayList<>(collection);
            sortable.sort(new ReverseSortByDistance());
            for(int i = 0; i < sortable.size(); i++) {
                ans += sortable.get(i).getDistance() + " ";
            }
            return new Response(ans);
        }
        return new Response("wrong usage of this command");
    }

    @Override
    public String description() {
        return "print_field_descending_distance - prints all distances in descending order";
    }

    @Override
    public boolean checkArgs(String[] args) {
        return args.length == 0;
    }
}

class ReverseSortByDistance implements Comparator<Route> {
    /**
     * comparator for reversed sort
     * @param a
     * @param b
     * @return
     */
    public int compare(Route a, Route b) {
        if (a.getDistance() != null && b.getDistance() != null) {
            return Float.compare(b.getDistance(), a.getDistance());
        }
        else if(a.getDistance() != null) {
            return -1;
        }
        else if(b.getDistance() != null) {
            return 1;
        }
        else{
            return 0;
        }
    }
}