package org.unrealfff.commands;

import org.unrealfff.data.CollectionManager;
import org.unrealfff.data.unit.ArgPair;
import org.unrealfff.data.unit.Request;
import org.unrealfff.data.unit.Response;
import org.unrealfff.data.unit.Route;

public class Clear implements Operation {
    /**
     * clear - deletes all elements from collection
     */
    CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    @Override
    public Response execute(Request request) {
        if(this.checkArgs(request.args())) {
            return this.collectionManager.clear();
        }
        return new Response("wrong usage of command");
    }

    @Override
    public String description() {
        return "clear - deletes all elements from collection";
    }

    @Override
    public boolean checkArgs(String[] args) {
        return args.length == 0;
    }
}
