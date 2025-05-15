package org.unrealfff.commands;

import org.unrealfff.data.CollectionManager;
import org.unrealfff.data.unit.ArgPair;
import org.unrealfff.data.unit.Request;
import org.unrealfff.data.unit.Response;
import org.unrealfff.data.unit.Route;

public class ExecuteScript implements Operation {
    /**
     * execute_script filename - executes script from a fil
     */
    CollectionManager collectionManager;

    public ExecuteScript(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    @Override
    public Response execute(Request request) {
        if (this.checkArgs(request.args())) {
            return this.collectionManager.executeScript(this.getArgs(request.args()));
        }
        return new Response("wrong usage of this command");
    }

    @Override
    public String description() {
        return "execute_script filename - executes script from a file";
    }

    @Override
    public boolean checkArgs(String[] args) {
        return args.length == 1;
    }

    String getArgs(String[] args) {
        return args[0];
    }
}
