import { Component, OnInit, Output, EventEmitter, Input } from "@angular/core";
import {
  Validators,
  FormGroup,
  FormControl,
  FormBuilder
} from "@angular/forms";
import { Observable, of } from "rxjs";
import { filter, map } from "rxjs/operators";
import { CalendarEvent } from "calendar-utils";
import { UserService } from "../../../services/user.service";
import { SubjectService } from "../../../services/subject.service";
import { ReservationInfo } from "../../../models/ReservationInfo";

@Component({
  selector: "app-filter-reservation-form",
  templateUrl: "./filter-reservation-form.component.html",
  styleUrls: ["./filter-reservation-form.component.css"]
})
export class FilterReservationFormComponent implements OnInit {
  @Input()
  events$: Observable<Array<CalendarEvent<{ info: ReservationInfo }>>>;

  @Output()
  eventEmitter = new EventEmitter<
    Observable<Array<CalendarEvent<{ info: ReservationInfo }>>>
  >();

  filterValues: string[] = ["Tanár neve", "Tantárgy neve"];
  values: string[];

  selectedFilter = new FormControl("", [Validators.required]);

  selectedValue = new FormControl("", [Validators.required]);

  filterForm: FormGroup = this.builder.group({
    selectedFilter: this.selectedFilter,
    selectedValue: this.selectedValue
  });

  constructor(
    private builder: FormBuilder,
    private userService: UserService,
    private subjectService: SubjectService
  ) {}

  ngOnInit() {}

  onFilterSelect() {
    this.values = [];

    switch (this.selectedFilter.value) {
      case this.filterValues[0]: {
        this.userService.getNames().subscribe(res => {
          res.map(name => {
            this.values.push(name);
          });
        });
        break;
      }
      case this.filterValues[1]: {
        this.subjectService.getSubjectNames().subscribe(res => {
          res.map(name => {
            this.values.push(name);
          });
        });
        break;
      }
    }
  }

  onFilter() {
    this.events$.subscribe( res => console.log(res));

    switch (this.selectedFilter.value) {
      case this.filterValues[0]: {
        const filteredEvents$: Observable<Array<CalendarEvent<{ info: ReservationInfo }>>> = this.events$.pipe(
          map((events: CalendarEvent[]) => {
            return events.filter(event => {
              event.meta.teacherName === this.selectedValue.value;
            });
          })
        );

        filteredEvents$.subscribe(res => console.log(res));
      }
    }

    this.filterForm.reset();
  }
}
