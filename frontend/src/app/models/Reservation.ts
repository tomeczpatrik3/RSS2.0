import { ReservationService } from "../services/reservation.service";
import { makeDecorator } from "@angular/core/src/util/decorators";
import { stagger } from "@angular/animations/src/animation_metadata";
import { Classroom } from "./Classroom";
import { Subject } from "./Subject";
import { User } from "./User";

export class Reservation {
    name:       string;
    note:       string;
    type:       string;
    status:     string;
    classroom:  Classroom;
    subject:    Subject;
    user:       User;
    dates:      Date[];


    public constructor (
        name:     string,
        note:       string,
        type:       string,
        status:     string,
        user:       User,
        subject:    Subject,
        classroom:  Classroom,
        dates:      Date[]
    ) 
    {
        this.user = user;
        this.subject = subject;
        this.classroom = classroom;
        this.dates = dates;
        this.status = status;
        this.note = note;
        this.name = name;
        this.type = type;
    }
}