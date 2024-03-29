package com.schedule_management.event;

import com.schedule_management.event.update.AbstractAuditableEvent;
import com.schedule_management.event.update.UpdateMeeting;

import java.time.ZonedDateTime;
import java.util.Set;

public class Meeting extends AbstractEvent{

    private Set<String> participants;
    private String meetingRoom;
    private String agenda;

    public Meeting(int id, String title,
                    ZonedDateTime startAt, ZonedDateTime endAt,
                   Set<String> participants, String meetingRoom, String agenda) {
        super(id, title, startAt, endAt);

        this.participants = participants;
        this.meetingRoom = meetingRoom;
        this.agenda = agenda;
    }
    @Override
    public void print(){
        System.out.printf("[회의] %s : %s (참석자 : %s)%n", getTitle(), agenda , participants);

    }

    @Override
    public boolean support(EventType type) {
        return type == EventType.MEETING;
    }


    @Override
    protected void update(AbstractAuditableEvent update) {
        UpdateMeeting updateMeeting = (UpdateMeeting) update;
        this.participants = updateMeeting.getParticipants();
        this.meetingRoom = updateMeeting.getMeetingRoom();
        this.agenda = updateMeeting.getAgenda();
    }
}
