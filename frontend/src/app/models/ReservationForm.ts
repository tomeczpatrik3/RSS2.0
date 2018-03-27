export class ReservationForm {
    username: string;
    semester: string;
    subjectCode: string;
    buildingName: string;
    roomName: string;
    note: string;
    dates: string[];

    public constructor (
        username: string,
        semester: string,
        subjectCode: string,
        buildingName: string,
        roomName: string,
        note: string,
        dates: string[]
    ) {
        this.username = username;
        this.semester = semester;
        this.subjectCode = subjectCode;
        this.buildingName = buildingName;
        this.roomName = roomName;
        this.note = note;
        this.dates = dates;
    }
}