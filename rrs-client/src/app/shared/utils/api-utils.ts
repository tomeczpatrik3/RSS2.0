import { DialogService } from "../services/dialog.service";
import { TextUtils } from "./text-utils";
import { InfoDialogComponent } from "../components/dialogs/info-dialog/info-dialog.component";

export class ApiUtils {
  constructor(private dialogService: DialogService) {}

  public showError(title: string, error: any): void {
    this.dialogService.openDialog(
      title,
      TextUtils.addBreaks(error.error),
      InfoDialogComponent
    );
  }

  public showMessage(title: string, text: string): void {
    this.dialogService.openDialog(
      title,
      text,
      InfoDialogComponent
    );
  }
}
