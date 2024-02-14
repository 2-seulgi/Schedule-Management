package com.schedule_management.reader;

import com.opencsv.CSVReader;
import com.schedule_management.event.Meeting;
import com.schedule_management.event.NoDisturbance;
import com.schedule_management.event.OutOfOffice;
import com.schedule_management.event.Todo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.fasterxml.jackson.databind.cfg.CoercionInputShape.Array;

public class EventCsvReader {
    public List<Meeting> readMeetings(String path) throws IOException {
        List<Meeting> result = new ArrayList<>();

        // 데이터를 읽는 부분
        List<String[]> read = readAll(path);
        for(int i = 0 ; i< read.size(); i++){
            if(skipHeader(i)){
                continue;
            }
            String[] each = read.get(i);
            result.add(
                    new Meeting(
                            Integer.parseInt(each[0]),
                            each[2],
                            ZonedDateTime.of(
                                    LocalDateTime.parse(
                                            each[6],
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                    )
                                    , ZoneId.of("Asia/Seoul")
                            ),
                            ZonedDateTime.of(
                                    LocalDateTime.parse(
                                            each[7],
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                    )
                                    , ZoneId.of("Asia/Seoul")
                            ),
                            new HashSet<>(Arrays.asList(each[3].split(","))),
                            each[4],
                            each[5]
                    )
            );
        }
        // Meeting으로 변환 부분

        return result;
    }

    public List<NoDisturbance> readNoDisturbance(String path) throws IOException {
        List<NoDisturbance> result = new ArrayList<>();

        // 데이터를 읽는 부분
        List<String[]> read = readAll(path);
        for(int i = 0 ; i< read.size(); i++){
            if(skipHeader(i)){
                continue;
            }
            String[] each = read.get(i);
            result.add(
                    new NoDisturbance(
                            Integer.parseInt(each[0]),
                            each[2],
                            ZonedDateTime.of(
                                    LocalDateTime.parse(
                                            each[3],
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                    )
                                    , ZoneId.of("Asia/Seoul")
                            ),
                            ZonedDateTime.of(
                                    LocalDateTime.parse(
                                            each[4],
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                    )
                                    , ZoneId.of("Asia/Seoul")
                            )
                    )
            );
        }
        // Meeting으로 변환 부분

        return result;
    }

    public List<OutOfOffice> readOutOfOffice(String path) throws IOException {
        List<OutOfOffice> result = new ArrayList<>();

        // 데이터를 읽는 부분
        List<String[]> read = readAll(path);
        for(int i = 0 ; i< read.size(); i++){
            if(skipHeader(i)){
                continue;
            }
            String[] each = read.get(i);
            result.add(
                    new OutOfOffice(
                            Integer.parseInt(each[0]),
                            each[2],
                            ZonedDateTime.of(
                                    LocalDateTime.parse(
                                            each[3],
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                    )
                                    , ZoneId.of("Asia/Seoul")
                            ),
                            ZonedDateTime.of(
                                    LocalDateTime.parse(
                                            each[4],
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                    )
                                    , ZoneId.of("Asia/Seoul")
                            )
                    )
            );
        }
        // Meeting으로 변환 부분

        return result;
    }

    public List<Todo> readTodos(String path) throws IOException {
        List<Todo> result = new ArrayList<>();

        // 데이터를 읽는 부분
        List<String[]> read = readAll(path);
        for(int i = 0 ; i< read.size(); i++){
            if(skipHeader(i)){
                continue;
            }
            String[] each = read.get(i);
            result.add(
                    new Todo(
                            Integer.parseInt(each[0]),
                            each[2],
                            ZonedDateTime.of(
                                    LocalDateTime.parse(
                                            each[4],
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                    )
                                    , ZoneId.of("Asia/Seoul")
                            ),
                            ZonedDateTime.of(
                                    LocalDateTime.parse(
                                            each[5],
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                    )
                                    , ZoneId.of("Asia/Seoul")
                            ),
                            each[3]
                    )
            );
        }
        // Meeting으로 변환 부분

        return result;
    }

    private boolean skipHeader(int i) {
        return i == 0;
    }

    public List<String[]> readAll(String path) throws IOException {
        InputStream in = getClass().getResourceAsStream(path);
        InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);

        CSVReader csvReader = new CSVReader(reader);
        return csvReader.readAll();
    }
}
