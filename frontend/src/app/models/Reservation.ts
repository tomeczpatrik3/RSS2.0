export class Reservation {
    username: string;
    name: string;
    room: string;
    subjectName: string;
    subjectCode: string;
    building: string;
    startDate: Date;
    endDate: Date;
    day: string;
    startTime: string;
    endTime: string;

    public constructor (
        username: string,
        room: string, 
        subjectName: string,
        subjectCode: string,
        building: string,
        startDate: Date,
        endDate: Date, 
        day: string, 
        startTime: string, 
        endTime: string,
        name?: string
    ) 
    {
        this.username = username;
        this.startDate = startDate;
        this.endDate = endDate;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.building = building;
        this.room = room;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name || "";
    }
}