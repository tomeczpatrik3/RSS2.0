import { BaseReservation } from "./BaseReservation";

export class EventReservation extends BaseReservation {
    eventName:      string;
    date:           string;
    startTime:      string;
    endTime:        string;

    public constructor(
        username:       string,
        buildingName:   string,
        roomName:       string,
        note:           string,
        eventName:      string,
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
        this.eventName = eventName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}