package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId,Integer.MAX_VALUE);
        this.calendar = new ArrayList<>();
    }
    // add meeting method
    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        int countMeetings = 0;
//        Collections.sort(calendar, (a,b)-> (a.getStartTime().isBefore(b.getStartTime()))
//                        ? -1
//                        : (a.getStartTime().isAfter(b.getStartTime()) ? 1 : 0));
        Collections.sort(calendar, (a,b)-> (a.getEndTime().isBefore(b.getEndTime()))
                        ? -1
                        : (a.getEndTime().isAfter(b.getEndTime()) ? 1 : 0));
        for(Meeting m : calendar) {
            System.out.print(m.getStartTime() +" "+ m.getEndTime()+" ");
        }

        if(calendar.isEmpty()) return countMeetings;
        countMeetings=1;
        LocalTime prevEndTime = calendar.get(0).getEndTime();
        for(int i=1;i<calendar.size();i++){
            if(calendar.get(i).getStartTime().isBefore(prevEndTime)){
                continue;
            }
            else if(!calendar.get(i).getStartTime().isBefore(prevEndTime) &&
                    !calendar.get(i).getStartTime().isAfter(prevEndTime)){
                continue;
            }
            else {
                countMeetings++;
                prevEndTime = calendar.get(i).getEndTime();
            }
        }
        //System.out.println();
        return countMeetings;
    }
}
