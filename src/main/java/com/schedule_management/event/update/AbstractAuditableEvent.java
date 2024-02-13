package com.schedule_management.event.update;

import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public abstract class AbstractAuditableEvent {
    private final String title;
    private final ZonedDateTime startAt;
    private final ZonedDateTime endAt;

    protected AbstractAuditableEvent(String title, ZonedDateTime startAt, ZonedDateTime endAt){
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
    }

}
