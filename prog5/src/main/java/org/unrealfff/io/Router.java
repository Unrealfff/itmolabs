package org.unrealfff.io;
import org.unrealfff.commands.*;

import java.util.ArrayDeque;
import java.util.HashMap;
import org.unrealfff.data.CollectionManager;
import org.unrealfff.data.unit.Request;
import org.unrealfff.data.unit.Response;
import org.unrealfff.data.unit.Route;

public class Router {
    /**
     * Router identifies what command is used
     */
    private ArrayDeque<String> deque = new ArrayDeque<>();
    private HashMap<String, Operation> commands = new HashMap<>();
    private Handler handler;
    private CollectionManager cm;
    private boolean interactive_mode = true;

    public Router(Handler handler, String args) {
        this.handler = handler;
        this.cm = new CollectionManager(this, args);
        this.commands.put("add", new Add(cm, handler));
        this.commands.put("exit", new Exit(this.handler));
        this.commands.put("remove_by_id", new RemoveById(cm));
        this.commands.put("update", new Update(cm, this.handler));
        this.commands.put("help", new Help(this));
        this.commands.put("history", new History(this));
        this.commands.put("clear", new Clear(cm));
        this.commands.put("save", new Save(cm));
        this.commands.put("show", new Show(cm));
        this.commands.put("remove_any_by_distance", new RemoveAnyByDistance(cm));
        this.commands.put("count_greater_than_distance", new CountGreaterThanDistance(cm));
        this.commands.put("print_field_descending_distance", new PrintFieldDescendingDistance(cm));
        this.commands.put("add_if_min", new AddIfMin(cm, this.handler));
        this.commands.put("remove_lower", new RemoveLower(cm, this.handler));
        this.commands.put("execute_script", new ExecuteScript(cm));
        this.commands.put("info", new Info(cm));
    }

    public Response route (Request request) {
        try {
            this.deque.add(request.command());
            return this.commands.get(request.command()).execute(request);
        }
        catch (NullPointerException e) {
            return new Response("no such command");
        }
    }
    public HashMap<String, Operation> getCommands() {
        return this.commands;
    }
    public ArrayDeque<String> History() {
        return this.deque;
    }

    Integer getMaxId() {
        String[] args = {};
        return Integer.valueOf(new GetMaxId(cm).execute(new Request("_get_max_id", args)).text());
    }

    public void chmod(String io) {
        this.handler.setScanner(io);
    }

}
