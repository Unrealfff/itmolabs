package org.unrealfff.commands;

import org.unrealfff.data.CollectionManager;
import org.unrealfff.data.unit.ArgPair;
import org.unrealfff.data.unit.Request;
import org.unrealfff.data.unit.Response;
import org.unrealfff.io.Handler;

public class Update implements Operation {
    /**
     * update id {element} - updates every field of route in the collection
     */
    CollectionManager collectionManager;
    Handler handler;

    public Update(CollectionManager collectionManager, Handler handler) {
        this.collectionManager = collectionManager;
        this.handler = handler;
    }

    public Update() {

    }

    @Override
    public Response execute(Request request) {
        if (this.checkArgs(request.args())) {
            ArgPair argPair = this.getArgs(request.args());
            return this.collectionManager.update(argPair.arg1(), argPair.arg2());
        }
        return new Response("wrong usage of this command");
    }
    @Override
    public String description() {
        return "update id {element} - updates every field of route in the collection";
    }

    @Override
    public boolean checkArgs(String[] args) {
        if (args.length == 1) {
            try {
                Integer.valueOf(args[0]);
                return true;
            }
            catch (java.lang.NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    private ArgPair getArgs(String[] args) {
        return new ArgPair(this.handler.getRoute(), Integer.valueOf(args[0]));
    }
}
