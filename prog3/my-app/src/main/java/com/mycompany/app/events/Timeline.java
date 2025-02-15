package com.mycompany.app.events;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Timeline {

    private Map<Integer, ArrayList<String>> triggers = new HashMap<>();
    private Events events = new Events();
    private int time = 0;


    public void addParticipant(String event, Participant participant) {
        this.events.addParticipant(event, participant);
    }

    public void callEvent(String event) {
        this.events.callEvent(event);
    }

    public void addTrigger(int time, String event) {
        if(!this.triggers.containsKey(time)) {
            this.triggers.put(time, new ArrayList<String>());
        }
        this.triggers.get(time).add(event);
    }

    private void callTime(int time) {
        if (this.triggers.get(time) != null) {
            for (int i = 0; i < this.triggers.get(time).size(); i++) {
                this.events.callEvent(this.triggers.get(time).get(i));
            }
        }
    }

    public void tick() {
        this.time++;
        this.callTime(this.time);
    }

}