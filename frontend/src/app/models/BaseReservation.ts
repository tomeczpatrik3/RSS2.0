export abstract class BaseReservation {
    username:       string;
    buildingName:   string;
    roomName:       string;
    note:           string;

    public constructor(
        username:       string,
        buildingName:   string,
        roomName:       string,
        note:           string
    ) {
        this.username = username;
        this.buildingName = buildingName;
        this.roomName = roomName;
        this.note = note;
    }
}