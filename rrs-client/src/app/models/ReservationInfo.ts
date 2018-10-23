export class ReservationInfo {
    id: number;
    type: string;
    name: string;
    building: string;
    classroom: string;
    note: string;
    eventName: string;
    semester: string;
    subject: string;

    constructor(
        id: number,
        type: string,
        name: string,
        building: string,
        classroom: string,
        note: string,
        eventName: string,
        semester: string,
        subject: string
    ) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.building = building
        this.classroom = classroom;
        this.note = note;
        this.eventName = eventName;
        this.semester = semester;
        this.subject = subject;
    }
}