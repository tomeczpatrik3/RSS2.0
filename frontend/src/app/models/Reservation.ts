export class Reservation {
    username: string;
    name: string;
    subjectName: string;
    subjectCode: string;
    building: string;
    room: string;
    day: string;
    startTime: string;
    endTime: string;
    startDate: Date;
    endDate: Date;
    status: string;

    public constructor (
        username?: string,
        subjectName?: string,
        subjectCode?: string,
        building?: string,
        room?: string,
        day?: string,
        startTime?: string,
        endTime?: string,
        startDate?: Date,
        endDate?: Date,
        name?: string,
        status?: string,
    ) 
    {
        this.username = username || "";
        this.subjectName = subjectName || "";
        this.subjectCode = subjectCode || "";
        this.building = building || "";
        this.room = room || "";
        this.day = day || "";
        this.startTime = startTime || "";
        this.endTime = endTime || "";
        this.startDate = startDate || new Date();
        this.endDate = endDate || new Date();
        this.name = name || "";
        this.status = status || "";
    }
}