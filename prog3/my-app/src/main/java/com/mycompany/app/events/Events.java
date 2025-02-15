package com.mycompany.app.events;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Events {

    private Map<String, ArrayList<Participant>> participants = new HashMap<>();

    public void addParticipant(String event, Participant participant) {
        if(!this.participants.containsKey(event)) {
            this.participants.put(event, new ArrayList<Participant>());
        }
        this.participants.get(event).add(participant);
    }

    public void callEvent(String event) {
        ArrayList<Participant> participantsOfEvent = this.participants.get(event);
        for(int i = 0; i < participantsOfEvent.size(); i++) {
            participantsOfEvent.get(i).participate(event);
        }
    } 

}