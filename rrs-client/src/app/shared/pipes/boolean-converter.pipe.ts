import { Pipe, PipeTransform } from "@angular/core";

/**
 * Boolean converter pipe
 * Használata: {{ érték | booleanConvert }}
 */
@Pipe({ name: "booleanConvert" })
export class BooleanConverterPipe implements PipeTransform {
  /**
   * Az átalakítást végző függvény
   * @param value Az átalakítandó érték
   */
  transform(value: boolean): string {
    if (value == true) {
      return "Van";
    } else if (value == false) {
      return "Nincs"
    } else {
      return value;
    }
  }
}
