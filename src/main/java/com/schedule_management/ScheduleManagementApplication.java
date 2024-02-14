package com.schedule_management;

import com.schedule_management.event.*;
import com.schedule_management.event.update.UpdateMeeting;
import com.schedule_management.reader.EventCsvReader;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class ScheduleManagementApplication {

    public static void main(String[] args) throws IOException {
        Schedule schedule = new Schedule();

        EventCsvReader csvReader = new EventCsvReader();
        String meetingCsvPath = "/data/Meeting.csv";
        String noDisturbanceCsvPath = "/data/no_disturbance.csv";
        String outOfOfficeCsvPath = "/data/out_of_office.csv";
        String todoCsvPath = "/data/to_do.csv";

        List<Meeting> meetings = csvReader.readMeetings(meetingCsvPath);
        meetings.forEach(schedule::add);
//
//        Meeting meeting = meetings.get(0);
//        meeting.print();
//        meeting.delete(true);
//        System.out.println("삭제 후 수정 -----");
//        meetings.get(0).validateAndUpdate(
//                new UpdateMeeting(
//                        "업무 공유"
//                        , ZonedDateTime.now()
//                        , ZonedDateTime.now()
//                        , null
//                        ,"A"
//                        ,"new agenda"
//                )
//        );
//        meeting.print();

        List<NoDisturbance> noDisturbances = csvReader.readNoDisturbance(noDisturbanceCsvPath);
        noDisturbances.forEach(schedule::add);

        List<OutOfOffice> outOfOffices = csvReader.readOutOfOffice(outOfOfficeCsvPath);
        outOfOffices.forEach(schedule::add);

        List<Todo> todos = csvReader.readTodos(todoCsvPath);
        todos.forEach(schedule::add);



        schedule.printAll();

    }

}
