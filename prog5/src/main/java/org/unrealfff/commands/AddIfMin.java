package org.unrealfff.commands;

import org.unrealfff.data.CollectionManager;
import org.unrealfff.data.unit.ArgPair;
import org.unrealfff.data.unit.Request;
import org.unrealfff.data.unit.Response;
import org.unrealfff.data.unit.Route;
import org.unrealfff.io.Handler;
import org.unrealfff.io.Router;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class AddIfMin implements Operation {
    /**
     * add_if_min {element} - adds an element if it's lower than min element in collection
     */
    CollectionManager collectionManager;
    Handler handler;

    public AddIfMin(CollectionManager collectionManager, Handler handler) {
        this.collectionManager = collectionManager;
        this.handler = handler;
    }



    @Override
    public Response execute(Request request) {
        if (this.checkArgs(request.args())) {
            LinkedHashSet<Route> collection = this.collectionManager.getCollection();
            Iterator<Route> iter = collection.iterator();
            Float minel = Float.MAX_VALUE;
            while (iter.hasNext()) {
                Route i = iter.next();
                if (i.getDistance() != null) {
                    if (Float.compare(minel, i.getDistance()) > 0) {
                        minel = i.getDistance();
                    }
                }
            }
            Route route = this.getArgs();
            if (route.getDistance() != null && Float.compare(minel, route.getDistance()) > 0) {
                return this.collectionManager.add(route);
            }
            return new Response("not lower than min element");
        }
        return new Response("wrong usage of this command");
    }

    @Override
    public String description() {
        return "add_if_min {element} - adds an element if it's lower than min element in collection";
    }

    @Override
    public boolean checkArgs(String[] args) {
        return args.length == 0;
    }

    private Route getArgs() {
        return this.handler.getRoute();
    }
}

