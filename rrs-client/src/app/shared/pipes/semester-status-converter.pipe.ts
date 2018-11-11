import { Pipe, PipeTransform } from "@angular/core";

/**
 * Semester status converter pipe
 * Használata: {{ érték | semesterStatusConvert }}
 */
@Pipe({ name: "semesterStatusConvert" })
export class SemesterStatusConverterPipe implements PipeTransform {
  /**
   * Az átalakítást végző függvény
   * @param value Az átalakítandó érték
   */
  transform(value: boolean): string {
    if (value == true) {
      return "Nyitott";
    } else if (value == false) {
      return "Lezárt";
    }
  }
}
