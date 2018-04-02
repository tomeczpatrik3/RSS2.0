import { BaseReservation } from "./BaseReservation";

export class EventReservation extends BaseReservation {
    eventName:      string;
    startDateTime:  string;
    endDateTime:    string;

    public constructor(
        username:       string,
        buildingName:   string,
        roomName:       string,
        note:           string,
        eventName:      string,
        startDateTime:  string,
        endDateTime:    string
    ) {
        super(
            username,
            buildingName,
            roomName,
            note
        );
        this.eventName = eventName;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}