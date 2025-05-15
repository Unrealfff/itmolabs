package org.unrealfff.commands;

import org.unrealfff.data.CollectionManager;
import org.unrealfff.data.unit.ArgPair;
import org.unrealfff.data.unit.Request;
import org.unrealfff.data.unit.Response;
import org.unrealfff.data.unit.Route;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class CountGreaterThanDistance implements Operation {
    /**
     * count_greater_than_distance distance - number of elements with distance greater then given
     */
    CollectionManager collectionManager;

    public CountGreaterThanDistance(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }



    @Override
    public Response execute(Request request) {
        if (this.checkArgs(request.args())) {
            LinkedHashSet<Route> collection = this.collectionManager.getCollection();
            Iterator<Route> iter = collection.iterator();
            int ans = 0;
            while (iter.hasNext()) {
                Route i = iter.next();
                if (i.getDistance() != null) {
                    if (Float.compare(i.getDistance(), this.getArgs(request.args())) > 0 && !i.getDistance().isNaN()) {
                        ans++;
                    }
                }
            }
            return new Response(String.valueOf(ans));
        }
        return new Response("wrong usage of this command");
    }

    @Override
    public String description() {
        return "count_greater_than_distance distance - number of elements with distance greater then given";
    }

    @Override
    public boolean checkArgs(String[] args) {
        if (args.length == 1) {
            try {
                Float.valueOf(args[0]);
                return true;
            }
            catch (java.lang.NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    private Float getArgs(String[] args) {
        return Float.valueOf(args[0]);
    }
}

