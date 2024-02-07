package com.schedule_management;

import com.schedule_management.event.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class ScheduleManagementApplication {

    public static void main(String[] args) {
        Schedule schedule = new Schedule();

        HashSet<String> participants = new HashSet<>();
        participants.add("bien");

        Meeting meeting1 = new Meeting(
                1, "meeting1",
                ZonedDateTime.now(), ZonedDateTime.now().plusHours(1),
                participants, "meetingRoomA",
                "스터디"
        );
        schedule.add(meeting1);

        Todo todo1 = new Todo(
                2, "todo1",
                ZonedDateTime.now().plusHours(3), ZonedDateTime.now().plusHours(4),
                "근태조정신청"
        );

        Todo todo2 = new Todo(
                3, "todo1",
                ZonedDateTime.now().plusHours(5), ZonedDateTime.now().plusHours(4),
                "테스트"
        );
        schedule.add(todo1);

        schedule.printAll();

    }

}
