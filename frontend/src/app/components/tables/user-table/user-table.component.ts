import { Component, OnInit } from '@angular/core';
import { User } from '../../../models/User';
import { UserService } from '../../../services/user.service';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css']
})
export class UserTableComponent implements OnInit {

  userData = new UserDataSource(this.userService);
  columnsToDisplay = ['username', 'name', 'email', 'role'];

  constructor(
    private userService: UserService
  ) {
  }

  ngOnInit() {
  }

}

export class UserDataSource extends DataSource<any> {

  constructor (
    private userService: UserService
  ) {
    super();
  }

  connect(): Observable<User[]> {
    return this.userService.getAll();
  }

  disconnect() {}
}
