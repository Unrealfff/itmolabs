package org.unrealfff.commands;

import org.unrealfff.data.CollectionManager;
import org.unrealfff.data.unit.*;
import org.unrealfff.io.Handler;
import org.unrealfff.io.Router;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class RemoveLower implements Operation {
    /**
     * remove_lower {element} - removes all element which are lower then given
     */
    CollectionManager collectionManager;
    Handler handler;

    public RemoveLower(CollectionManager collectionManager, Handler handler) {
        this.collectionManager = collectionManager;
        this.handler = handler;
    }



    @Override
    public Response execute(Request request) {
        if (this.checkArgs(request.args())) {
            LinkedHashSet<Route> collection = this.collectionManager.getCollection();
            Iterator<Route> iter = collection.iterator();
            Route route = this.getArgs();
            String ans = "";
            while (iter.hasNext()) {
                Route i = iter.next();
                if (new SortRouteByDistance().compare(route, i) > 0) {
                    ans += this.collectionManager.removeById(i.getId()).text() + "\n";
                }
            }
            if (!ans.isEmpty()) {
                return new Response(ans);
            }
            return new Response("no elements were deleted");
        }
        return new Response("wrong usage of this command");
    }

    @Override
    public String description() {
        return "remove_lower {element} - removes all element which are lower then given";
    }

    @Override
    public boolean checkArgs(String[] args) {
        return args.length == 0;
    }

    private Route getArgs() {
        return this.handler.getRoute();
    }
}

