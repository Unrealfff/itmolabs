package org.unrealfff.commands;

import org.unrealfff.data.CollectionManager;
import org.unrealfff.data.unit.Request;
import org.unrealfff.data.unit.Response;
import org.unrealfff.data.unit.Route;
import org.unrealfff.io.Router;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

public class Show implements Operation{
    /**
     * show - prints collection
     */
    CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        if(this.checkArgs(request.args())) {
            LinkedHashSet<Route> collection = this.collectionManager.getCollection();
            Iterator<Route> iter = collection.iterator();
            String ans = "";
            while(iter.hasNext()) {
                ans += iter.next().show() + "\n";
            }
            return new Response(ans);
        }
        return new Response("wrong usage of this command");
    }
    @Override
    public String description() {
        return "show - prints collection";
    }

    @Override
    public boolean checkArgs(String[] args) {
        return args.length == 0;
    }

}
