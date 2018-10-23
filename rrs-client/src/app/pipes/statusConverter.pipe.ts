import { Pipe, PipeTransform } from "@angular/core";
import { Statuses } from "../config/statuses.config";

/**
 * Példa a használatra:
 * {{ value | statusConvert }}
 */
@Pipe({ name: "statusConvert" })
export class StatusConverterPipe implements PipeTransform {
  transform(value: string): string {
    if (value === Statuses.ACCEPTED) {
      return "Elfogadva";
    } else if (value === Statuses.PENDING) {
      return "Folyamatban";
    } else if (value === Statuses.DECLINED) {
      return "Elutasítva";
    } else {
      return value;
    }
  }
}
