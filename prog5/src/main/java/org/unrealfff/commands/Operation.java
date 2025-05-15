package org.unrealfff.commands;

import org.unrealfff.data.unit.Request;
import org.unrealfff.data.unit.Response;
import org.unrealfff.data.unit.Route;

public interface Operation {
    /**
     * Operation is a command interface
     * @param request
     * @return
     */
    Response execute(Request request);
    String description();
    boolean checkArgs(String[] args);
}