package com.schedule_management.event;

import com.schedule_management.event.update.AbstractAuditableEvent;

import java.time.ZonedDateTime;

public class NoDisturbance extends AbstractEvent{

    public NoDisturbance(int id, String title,
                ZonedDateTime startAt, ZonedDateTime endAt)
    {
        super(id, title, startAt, endAt);
    }

    @Override
    protected void update(AbstractAuditableEvent update) {

    }

    @Override
    public void print() {
        System.out.printf("[방해금지] %s : 방해금지기간(%s - %s)%n", getTitle(), getStartAt(), getEndAt());
    }

    @Override
    public boolean support(EventType type) {
        return type == EventType.NO_DISTURBANCE;
    }
}
