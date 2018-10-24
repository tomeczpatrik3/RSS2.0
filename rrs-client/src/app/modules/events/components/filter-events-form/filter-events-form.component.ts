import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import {
  Validators,
  FormGroup,
  FormControl,
  FormBuilder
} from "@angular/forms";
import { Observable } from "rxjs";
import { ReservationEvent } from "../../../../shared/models/ReservationEvent";
import { Filter } from "../../../../shared/enums/Filter";
import { UserApiService } from "../../../../shared/services/api/user.api.service";
import { BuildingApiService } from "../../../../shared/services/api/building.api.service";
import { ClassroomApiService } from "../../../../shared/services/api/classroom.api.service";
import { SubjectApiService } from "../../../../shared/services/api/subject.api.service";
import { SemesterApiService } from "../../../../shared/services/api/semester.api.service";
import { EventReservationApiService } from "../../../../shared/services/api/event-reservation.api.service";
import { EventsDataService } from "../../events.data.service";

@Component({
  selector: "app-filter-events-form",
  templateUrl: "./filter-events-form.component.html",
  styleUrls: ["./filter-events-form.component.css"]
})
export class FilterEventsFormComponent implements OnInit {
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
    private userService: UserApiService,
    private buildingService: BuildingApiService,
    private classroomService: ClassroomApiService,
    private subjectService: SubjectApiService,
    private semesterService: SemesterApiService,
    private eventReservationService: EventReservationApiService,
    private eventsService: EventsDataService
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
          this.eventsService.findByUserName(this.selectedValue.value)
        );
        break;
      }
      case Filter.BUILDING_NAME: {
        this.eventEmitter.emit(
          this.eventsService.findByBuildingName(this.selectedValue.value)
        );
        break;
      }
      /*       case Filter.CLASSROOM_NAME: {
        this.eventsService.getNamesByBuilding().subscribe(res => {
          res.map(name => {
            this.loadedValues.push(name);
          });
        });
        break;
      } */
      case Filter.SUBJECT_NAME: {
        this.eventEmitter.emit(
          this.eventsService.findBySubjectName(this.selectedValue.value)
        );
        break;
      }
      case Filter.SEMESTER_NAME: {
        this.eventEmitter.emit(
          this.eventsService.findBySemesterName(this.selectedValue.value)
        );
        break;
      }
      case Filter.EVENT_NAME: {
        this.eventEmitter.emit(
          this.eventsService.findByEventName(this.selectedValue.value)
        );
        break;
      }
    }
  }
}
