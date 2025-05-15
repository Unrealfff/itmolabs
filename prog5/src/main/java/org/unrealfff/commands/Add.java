package org.unrealfff.commands;

import org.unrealfff.data.CollectionManager;
import org.unrealfff.data.unit.Request;
import org.unrealfff.data.unit.Response;
import org.unrealfff.data.unit.Route;
import org.unrealfff.io.Handler;

public class Add implements Operation {
    /**
     * add {element} - adds an element to collection
     */
    CollectionManager collectionManager;
    Handler handler;

    public Add(CollectionManager collectionManager, Handler handler) {
        this.collectionManager = collectionManager;
        this.handler = handler;
    }

    @Override
    public Response execute(Request request) {
        if (this.checkArgs(request.args())) {
            return this.collectionManager.add(this.getArgs());
        }
        return new Response("wrong usage of command");
    }

    @Override
    public String description() {
        return "add {element} - adds an element to collection";
    }

    @Override
    public boolean checkArgs(String[] args) {
        return args.length == 0;
    }

    private Route getArgs() {
            return this.handler.getRoute();
    }
}
