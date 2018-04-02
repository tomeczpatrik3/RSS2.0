import { BaseReservation } from "./BaseReservation";

export class SimpleReservation extends BaseReservation {
    semesterName:   string;
    subjectCode:    string;
    startDateTime:  string;
    endDateTime:    string;

    public constructor(
        username:       string,
        buildingName:   string,
        roomName:       string,
        note:           string,
        semesterName:   string,
        subjectCode:    string,
        startDateTime:  string,
        endDateTime:    string
    ) {
        super(
            username,
            buildingName,
            roomName,
            note
        );
        this.semesterName = semesterName;
        this.subjectCode = subjectCode;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}