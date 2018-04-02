import { BaseReservation } from "./BaseReservation";

export class SemesterReservation extends BaseReservation {
    semesterName:   string;
    subjectCode:    string;
    day:            string;
    startTime:      string;
    endTime:        string;

    public constructor(
        username:       string,
        buildingName:   string,
        roomName:       string,
        note:           string,
        semesterName:   string,
        subjectCode:    string,
        day:            string,
        startTime:      string,
        endTime:        string
    ) {
        super(
            username,
            buildingName,
            roomName,
            note
        );
        this.semesterName = semesterName;
        this.subjectCode = subjectCode;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}