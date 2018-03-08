export class Ticket {
    status: string;
    lastModification: string;
    message: string;

    public constructor(status: string, lastModification:string, message: string) {
        this.status = status;
        this.lastModification = lastModification;
        this.message = message;
    }
}