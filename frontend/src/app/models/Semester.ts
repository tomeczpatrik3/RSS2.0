export class Semester {
    name: string;
    startDate: Date;
    endDate: Date;

    public constructor (
        name: string,
        startDate: Date,
        endDate: Date
    ) {
        this.name =         name;
        this.startDate =    startDate;
        this.endDate =      endDate;
    }
}