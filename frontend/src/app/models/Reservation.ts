import { ReservationService } from "../services/reservation.service";
import { makeDecorator } from "@angular/core/src/util/decorators";
import { stagger } from "@angular/animations/src/animation_metadata";
import { Classroom } from "./Classroom";
import { Subject } from "./Subject";
import { User } from "./User";

export class Reservation {
    note: string;
    classroom: Classroom;
    subject: Subject;
    user: User;
    status: string;
    dates: Date[];


    public constructor (
        user: User,
        subject: Subject,
        classroom: Classroom,
        dates: Date[],
        status: string,
        note: string
    ) 
    {
        this.user = user;
        this.subject = subject;
        this.classroom = classroom;
        this.dates = dates;
        this.status = status;
        this.note = note;
    }
}