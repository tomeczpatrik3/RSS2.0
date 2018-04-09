import { ReservationService } from "../services/reservation.service";
import { makeDecorator } from "@angular/core/src/util/decorators";
import { stagger } from "@angular/animations/src/animation_metadata";
import { Classroom } from "./Classroom";
import { Subject } from "./Subject";
import { User } from "./User";
import { Building } from "./Building";
import { Type } from "@angular/compiler/src/core";

export class Reservation {
    name:       string;
    note:       string;
    status:     string;
    type:       Type;
    building:   Building;
    classroom:  Classroom;
    subject:    Subject;
    user:       User;
    dates:      Date[];


    public constructor (
        name:     string,
        note:       string,
        status:     string,
        type:       Type,
        user:       User,
        building:   Building,
        subject:    Subject,
        classroom:  Classroom,
        dates:      Date[]
    ) 
    {
        this.user = user;
        this.subject = subject;
        this.building = building;
        this.classroom = classroom;
        this.dates = dates;
        this.status = status;
        this.note = note;
        this.name = name;
        this.type = type;
    }
}