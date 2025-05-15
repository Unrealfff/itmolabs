package org.unrealfff.commands;

import org.unrealfff.data.CollectionManager;
import org.unrealfff.data.unit.Request;
import org.unrealfff.data.unit.Response;
import org.unrealfff.data.unit.Route;
import org.unrealfff.io.Router;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

public class Info implements Operation{
    /**
     * info - shows information about collection
     */
    CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        if(this.checkArgs(request.args())) {
            String ans = "type - " + this.collectionManager.getTypeOfCollection() + "\n" +
                    "size - " + this.collectionManager.size() + "\n" +
                    "initialization time - " + this.collectionManager.getInitTime();

            return new Response(ans);
        }
        return new Response("wrong usage of this command");
    }
    @Override
    public String description() {
        return "info - shows information about collection";
    }

    @Override
    public boolean checkArgs(String[] args) {
        return args.length == 0;
    }

}
