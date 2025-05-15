package org.unrealfff.commands;

import org.unrealfff.data.unit.Request;
import org.unrealfff.data.unit.Response;
import org.unrealfff.data.unit.Route;
import org.unrealfff.io.Router;

import java.util.Map;

public class Help implements Operation{
    /**
     * help - display information about commands
     */
    Router router;
    public Help(Router rt) {
        this.router = rt;
    }

    @Override
    public Response execute(Request request) {
        if (this.checkArgs(request.args())) {
            String ans = "";
            for (Map.Entry<String, Operation> set :
                    this.router.getCommands().entrySet()) {
                ans += set.getValue().description() + "\n";
            }
            return new Response(ans);
        }
        return new Response("wrong usage of this command");
    }
    @Override
    public String description() {
        return "help - display information about commands";
    }

    @Override
    public boolean checkArgs(String[] args) {
        return args.length == 0;
    }

}
