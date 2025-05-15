package org.unrealfff.commands;

import org.unrealfff.data.CollectionManager;
import org.unrealfff.data.unit.ArgPair;
import org.unrealfff.data.unit.Request;
import org.unrealfff.data.unit.Response;
import org.unrealfff.data.unit.Route;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class GetMaxId implements Operation {
    /**
     * _get_max_id - gives you max id
     */
    CollectionManager collectionManager;

    public GetMaxId(CollectionManager collectionManager) {
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
                if (i.getId() > ans) {
                    ans = i.getId();
                }
            }
            return new Response(String.valueOf(ans + 1));
        }
        return new Response("wrong usage of this command");
    }

    @Override
    public String description() {
        return "_get_max_id - gives you max id";
    }

    @Override
    public boolean checkArgs(String[] args) {
        return args.length == 0;
    }
}

