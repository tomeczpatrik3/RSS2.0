import { Component, OnInit } from "@angular/core";
import {
  Validators,
  FormGroup,
  FormControl,
  FormBuilder
} from "@angular/forms";
import { ReservationService } from "../../../services/reservation.service";
import { UserService } from "../../../services/user.service";
import { ClassroomService } from "../../../services/classroom.service";
import { SubjectService } from "../../../services/subject.service";

@Component({
  selector: "app-filter-reservation-form",
  templateUrl: "./filter-reservation-form.component.html",
  styleUrls: ["./filter-reservation-form.component.css"]
})
export class FilterReservationFormComponent implements OnInit {
  filterValues: string[] = ["Tantárgy neve", "Tanár neve"];
  values: string[];

  selectedFilter = new FormControl("", [Validators.required]);

  selectedValue = new FormControl("", [Validators.required]);

  filterForm: FormGroup = this.builder.group({
    selectedFilter: this.selectedFilter,
    selectedValue: this.selectedValue
  });

  constructor(
    private builder: FormBuilder,
    private reservationService: ReservationService,
    private userService: UserService,
    private classroomService: ClassroomService,
    private subjectService: SubjectService
  ) {}

  ngOnInit() {}

  async selectFilter() {
    switch (this.selectedFilter.value) {
      case this.filterValues[0]: {
        //statements;
        break;
      }
      case this.filterValues[1]: {
        this.userService.getNames().subscribe(res => (this.values = res));
        break;
      }
    }
  }

  filter() {
    console.log(this.filterForm.value);

    this.filterForm.reset();
  }
}
