import { Pipe, PipeTransform } from "@angular/core";
import { Statuses } from "../config/statuses.config";

/**
 * Status converter pipe
 * Használata: {{ érték | statusConvert }}
 */
@Pipe({ name: "statusConvert" })
export class StatusConverterPipe implements PipeTransform {
  /**
   * Az átalakítást végző függvény
   * @param value Az átalakítandó
   */
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
