package org.unrealfff.commands;

import org.unrealfff.data.unit.Request;
import org.unrealfff.data.unit.Response;
import org.unrealfff.data.unit.Route;
import org.unrealfff.io.Router;

import java.util.Iterator;
import java.util.Map;

public class History implements Operation{
    /**
     * history - displays last 9 commands
     */
    Router router;
    public History(Router rt) {
        this.router = rt;
    }

    @Override
    public Response execute(Request request) {
        if (this.checkArgs(request.args())) {
            String ans = "";
            Iterator value = this.router.History().iterator();
            int i = 0;
            while (value.hasNext() && i < 9) {
                i++;
                ans += value.next() + "\n";
            }
            return new Response(ans);
        }
        return new Response("wrong usage of this command");
    }

    @Override
    public String description() {
        return "history - displays last 9 commands";
    }

    @Override
    public boolean checkArgs(String[] args) {
        return args.length == 0;
    }

}
