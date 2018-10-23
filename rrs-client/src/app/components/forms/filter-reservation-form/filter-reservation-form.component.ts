import { Component, OnInit, Output, EventEmitter, Input } from "@angular/core";
import {
  Validators,
  FormGroup,
  FormControl,
  FormBuilder
} from "@angular/forms";
import { Observable, of } from "rxjs";
import { UserService } from "../../../services/user.service";
import { SubjectService } from "../../../services/subject.service";
import { BuildingService } from "../../../services/building.service";
import { EventService } from "../../../services/event.service";
import { ReservationEvent } from "../../../models/ReservationEvent";
import { Filter } from "../../../enums/Filter";
import { ClassroomService } from "../../../services/classroom.service";
import { SemesterService } from "../../../services/semester.service";
import { EventReservationService } from "../../../services/event-reservation.service";

@Component({
  selector: "app-filter-reservation-form",
  templateUrl: "./filter-reservation-form.component.html",
  styleUrls: ["./filter-reservation-form.component.css"]
})
export class FilterReservationFormComponent implements OnInit {
  @Output()
  eventEmitter = new EventEmitter<Observable<ReservationEvent[]>>();

  filterValues: string[] = [
    Filter.USER_NAME,
    Filter.BUILDING_NAME,
    //Filter.CLASSROOM_NAME,
    Filter.SUBJECT_NAME,
    Filter.SEMESTER_NAME,
    Filter.EVENT_NAME
  ];
  classroomFilterName: string = Filter.CLASSROOM_NAME;

  loadedBuildings: string[];
  loadedValues: string[];

  selectedFilter = new FormControl("", [Validators.required]);

  selectedValue = new FormControl("", [Validators.required]);

  selectedPlusValue = new FormControl("");

  filterForm: FormGroup = this.builder.group({
    selectedFilter: this.selectedFilter,
    selectedValue: this.selectedValue,
    selectedPlusValue: this.selectedPlusValue
  });

  constructor(
    private builder: FormBuilder,
    private userService: UserService,
    private buildingService: BuildingService,
    private classroomService: ClassroomService,
    private subjectService: SubjectService,
    private semesterService: SemesterService,
    private eventReservationService: EventReservationService,
    private eventService: EventService
  ) {}

  ngOnInit() {}

  onFilterSelect() {
    this.loadedValues = [];

    switch (this.selectedFilter.value) {
      case Filter.USER_NAME: {
        this.userService.getNames().subscribe(res => {
          res.map(name => {
            this.loadedValues.push(name);
          });
        });
        break;
      }
      case Filter.BUILDING_NAME: {
        this.buildingService.getNames().subscribe(res => {
          res.map(name => {
            this.loadedValues.push(name);
          });
        });
        break;
      }
      case Filter.CLASSROOM_NAME: {
/*         this.classroomService.getNamesByBuilding().subscribe(res => {
          res.map(name => {
            this.loadedValues.push(name);
          });
        });
        break; */
      }
      case Filter.SUBJECT_NAME: {
        this.subjectService.getSubjectNames().subscribe(res => {
          res.map(name => {
            this.loadedValues.push(name);
          });
        });
        break;
      }
      case Filter.SEMESTER_NAME: {
        this.semesterService.getSemesterNames().subscribe(res => {
          res.map(name => {
            this.loadedValues.push(name);
          });
        });
        break;
      }
      case Filter.EVENT_NAME: {
        this.eventReservationService.getEventNames().subscribe(res => {
          res.map(name => {
            this.loadedValues.push(name);
          });
        });
        break;
      }
    }
  }

  onFilter() {
    switch (this.selectedFilter.value) {
      case Filter.USER_NAME: {
        this.eventEmitter.emit(
          this.eventService.findByUserName(this.selectedValue.value)
        );
        break;
      }
      case Filter.BUILDING_NAME: {
        this.eventEmitter.emit(
          this.eventService.findByBuildingName(this.selectedValue.value)
        );
        break;
      }
      /*       case Filter.CLASSROOM_NAME: {
        this.classroomService.getNamesByBuilding().subscribe(res => {
          res.map(name => {
            this.loadedValues.push(name);
          });
        });
        break;
      } */
      case Filter.SUBJECT_NAME: {
        this.eventEmitter.emit(
          this.eventService.findBySubjectName(this.selectedValue.value)
        );
        break;
      }
      case Filter.SEMESTER_NAME: {
        this.eventEmitter.emit(
          this.eventService.findBySemesterName(this.selectedValue.value)
        );
        break;
      }
      case Filter.EVENT_NAME: {
        this.eventEmitter.emit(
          this.eventService.findByEventName(this.selectedValue.value)
        );
        break;
      }
    }
  }
}
