export class Reservation {
    username: string;
    roomName: string;
    subjectName: string;
    startDate: Date;
    endDate: Date;
    day: string;
    startTime: string;
    endTime: string;

    public constructor (
        username: string, 
        roomName: string, 
        subjectName: string, 
        startDate: Date,
         endDate: Date, 
         day: string, 
         startTime: string, 
         endTime: string) 
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.subjectName = subjectName;
        this.username = username;
        this.roomName = roomName;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}