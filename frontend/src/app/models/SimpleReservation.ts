import { BaseReservation } from "./BaseReservation";

export class SimpleReservation extends BaseReservation {
    semesterName:   string;
    subjectCode:    string;
    date:           string;
    startTime:      string;
    endTime:        string;

    public constructor(
        username:       string,
        buildingName:   string,
        roomName:       string,
        note:           string,
        semesterName:   string,
        subjectCode:    string,
        date:           string,
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
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}