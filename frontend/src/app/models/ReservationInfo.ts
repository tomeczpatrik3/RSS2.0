export class ReservationInfo {
    id: number;
    type: string;
    teacherName: string;

    constructor(
        id: number,
        type: string,
        teacherName: string
    ) {
        this.id = id;
        this.type = type;
        this.teacherName = teacherName;
    }
}