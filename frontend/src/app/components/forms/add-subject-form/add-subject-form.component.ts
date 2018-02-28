import { Component, OnInit } from '@angular/core';
import { Validators, FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { SnackbarService } from '../../../services/snackbar.service';
import { SubjectService } from '../../../services/subject.service';
import { Subject } from '../../../models/Subject';
import { MatDialog } from '@angular/material';
import { InfoDialogComponent } from '../../dialogs/info-dialog/info-dialog.component';

@Component({
  selector: 'app-add-subject-form',
  templateUrl: './add-subject-form.component.html',
  styleUrls: ['./add-subject-form.component.css']
})
export class AddSubjectFormComponent implements OnInit {

  subjectName = new FormControl('', [
    Validators.required,
    Validators.minLength(5),
    Validators.maxLength(30)
  ]);

  subjectForm: FormGroup = this.builder.group({
    subjectName: this.subjectName,
  });

  constructor(
    private builder: FormBuilder,
    private subjectService: SubjectService,
    private dialog: MatDialog
  ) { }

  ngOnInit() {
  }

  formToSubject(): Subject {
    return new Subject(
      this.subjectForm.value.subjectName
    );
  }

  /*
    Feliratkozunk, majd:
    - hiba esetén jelzünk a hibát dialog segítségével
    - siker esetén jelezzük a sikert dialog segítségével
  */
  addSubject() {
    this.subjectService.createSubject(this.formToSubject()).subscribe(
      res => console.log(res),
      error => this.openDialog("Tantárgy hozzáadása:", "Hiba történt"),
      () => this.openDialog("Tantárgy hozzáadása:", "Tantárgy sikeresen rögítve")
    );
    this.subjectForm.reset();
  }

  /*
    Dialog megjelenítése, valamint adatok átadása:
    title: a dialog címe
    text: a dialogban közölt üzenet

    Dialog bezárása után reseteljük a formot!
  */
  openDialog(title_: string, text_: string) {
    let dialogRef = this.dialog.open(InfoDialogComponent, {
      width: '600px',
      data: {
        title: title_,
        text: text_
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.subjectForm.reset();
    })
  }

}
