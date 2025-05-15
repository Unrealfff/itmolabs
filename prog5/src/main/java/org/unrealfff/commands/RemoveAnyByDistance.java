package org.unrealfff.commands;

import org.unrealfff.data.CollectionManager;
import org.unrealfff.data.unit.ArgPair;
import org.unrealfff.data.unit.Request;
import org.unrealfff.data.unit.Response;
import org.unrealfff.data.unit.Route;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class RemoveAnyByDistance implements Operation {
    /**
     * remove_any_by_distance distance - removes 1 element with given distance
     */
    CollectionManager collectionManager;

    public RemoveAnyByDistance(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }



    @Override
    public Response execute(Request request) {
        if (this.checkArgs(request.args())) {
            LinkedHashSet<Route> collection = this.collectionManager.getCollection();
            Iterator<Route> iter = collection.iterator();
            while (iter.hasNext()) {
                Route i = iter.next();
                if (i.getDistance() != null && i.getDistance().equals(this.getArgs(request.args()))) {
                    return this.collectionManager.removeById(i.getId());
                }
            }
            return new Response("no elements with such distance");
        }
        return new Response("wrong usage of this command");
    }
    @Override
    public String description() {
        return "remove_any_by_distance distance - removes 1 element with given distance";
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
