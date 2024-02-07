package com.schedule_management.event;

import java.util.ArrayList;
import java.util.List;

public class Schedule {

    public List<AbstractEvent> events = new ArrayList<>();

    public void add(AbstractEvent event){
        if(hasScheduleConflicWith(event)){
            return;
        }
        this.events.add(event);
    }
    public void printAll(){
        events.forEach(Event::print);
    }
    public void printBy(EventType type){
        events.stream()
                .filter(event-> event.support(EventType.MEETING))
                .forEach(Event::print);
    }

    private boolean hasScheduleConflicWith(AbstractEvent event){
        return events.stream()
                .anyMatch(each->
                    (event.getStartAt().isBefore(each.getEndAt())&& event.getStartAt().isAfter(each.getStartAt()))
                            || (event.getEndAt().isAfter(each.getStartAt()))&& event.getEndAt().isBefore(each.getEndAt()));

    }


}
