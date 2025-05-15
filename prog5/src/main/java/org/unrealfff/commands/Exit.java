package org.unrealfff.commands;

import org.unrealfff.data.unit.Request;
import org.unrealfff.data.unit.Response;
import org.unrealfff.io.Handler;

public class Exit implements Operation{
    /**
     * exit - exits programm
     */
    Handler handler;

    public Exit(Handler handler) {
        this.handler = handler;
    }

    @Override
    public Response execute(Request request) {
        if (this.checkArgs(request.args())) {
            this.handler.over();
            return new Response("terminating programm");
        }
        return new Response("wrong usage of this command");
    }

    @Override
    public String description() {
        return "exit - exits programm";
    }

    @Override
    public boolean checkArgs(String[] args) {
        return args.length == 0;
    }
}
