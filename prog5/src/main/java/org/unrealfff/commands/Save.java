package org.unrealfff.commands;

import org.unrealfff.data.CollectionManager;
import org.unrealfff.data.unit.ArgPair;
import org.unrealfff.data.unit.Request;
import org.unrealfff.data.unit.Response;
import org.unrealfff.data.unit.Route;

public class Save implements Operation {
    /**
     * save - saves collection to a file
     */
    CollectionManager collectionManager;

    public Save(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    @Override
    public Response execute(Request request) {
        if (this.checkArgs(request.args())) {
            return this.collectionManager.save();
        }
        return new Response("wrong usage of this command");
    }

    @Override
    public String description() {
        return "save - saves collection to a file";
    }

    @Override
    public boolean checkArgs(String[] args) {
        return args.length == 0;
    }
}
