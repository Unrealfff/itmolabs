package org.unrealfff.commands;

import org.unrealfff.data.CollectionManager;
import org.unrealfff.data.unit.Request;
import org.unrealfff.data.unit.Response;

public class RemoveById implements Operation {
    /**
     * remove_by_id id - removes an element by id
     */
    CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public RemoveById() {

    }

    @Override
    public Response execute(Request request) {
        return this.collectionManager.removeById(this.getArgs(request.args()));
    }

    @Override
    public String description() {
        return "remove_by_id id - removes an element by id";
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

    private Integer getArgs(String[] args) {
        return Integer.valueOf(args[0]);
    }
}
