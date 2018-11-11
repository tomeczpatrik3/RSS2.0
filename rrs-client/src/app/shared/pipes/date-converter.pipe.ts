import { Pipe, PipeTransform } from "@angular/core";
import { formatDate } from "@angular/common";

/**
 * Date converter pipe
 * Használata: {{ érték | dateConvert }}
 */
@Pipe({ name: "dateConvert" })
export class DateConverterPipe implements PipeTransform {
  /**
   * Az átalakítást végző függvény
   * @param value Az átalakítandó érték
   */
  transform(value: string, format:string): string {
    return formatDate(value, format, 'hu-HU');
  }
}
