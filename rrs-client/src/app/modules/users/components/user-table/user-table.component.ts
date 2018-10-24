import { Component, OnInit } from '@angular/core';
import { User } from '../../../../shared/models/User';
import { UserApiService } from '../../../../shared/services/api/user.api.service';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css']
})
export class UserTableComponent implements OnInit {

  users: User[];

  constructor(
    private userService: UserApiService
  ) {
  }

  ngOnInit() {
    this.userService.getAll().subscribe(
      res => this.users = res
    )
  }

}
