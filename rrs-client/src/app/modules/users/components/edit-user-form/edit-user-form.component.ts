import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { User } from "../../../../shared/models/User";
import { UsersDataService } from "../../users.data.service";
import { patterns } from "../../../../shared/utils/patterns";
import { TextUtils } from "../../../../shared/utils/text-utils";
import { DialogService } from "../../../../shared/services/dialog.service";
import { ErrorDialogComponent } from "../../../../shared/components/dialogs/error-dialog/error-dialog.component";

@Component({
  selector: "app-edit-user-form",
  templateUrl: "./edit-user-form.component.html",
  styleUrls: ["./edit-user-form.component.css"]
})
export class EditUserFormComponent implements OnInit {
  /*A felhasználó azonosítója*/
  @Input()
  userID: number;

  @Output()
  submitEvent = new EventEmitter<boolean>();

  /*A felhasználó*/
  model: User;
  /*E-mail pattern */
  emailPattern: string = patterns.email;

  constructor(
    private userService: UsersDataService,
    private dialogService: DialogService
  ) {}

  /**
   * Az inicializálásért felelős függvény
   */
  ngOnInit() {
    this.userService.findById(this.userID).subscribe(res => {
      this.model = res;
    });
  }

  /**
   * A módosításért felelős függvény
   */
  onSubmit() {
    this.userService
      .update(this.model.id, this.model)
      .subscribe(
        () => this.submitEvent.next(true),
        error =>
          this.dialogService.openDialog(
            "Felhasználó szerkesztése:",
            TextUtils.addBreaks(error.error),
            ErrorDialogComponent
          )
      );
  }

  /**
   * A kilépésért felelős függvény
   */
  onExit() {
    this.submitEvent.next(false);
  }
}
