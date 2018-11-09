import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { MessageTablePageComponent } from "./pages/message-table-page/message-table-page.component";
import { RoleGuard } from "../../shared/guards/role-guard.service";
import { Authorities } from "../../shared/config/authoritites.config";

const routes: Routes = [
  {
    path: "messages",
    component: MessageTablePageComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_USER
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MessagesRoutingModule {}
