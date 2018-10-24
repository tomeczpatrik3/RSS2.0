import { Pipe, PipeTransform } from "@angular/core";

/**
 * Empty pipe
 * Használata: {{ érték | empty }} vagy {{ érték | empty:"helyettesítő" }}
 */
@Pipe({ name: "empty" })
export class EmptyPipe implements PipeTransform {
  /**
   * Az átalakítást végző függvény
   * @param value Az átalakítandó érték
   * @param substitute A helyettesítő érték
   */
  transform(value: string, substitute: string): string {
    if (!value || value === "") {
      return substitute ? substitute : "-";
    } else {
      return value;
    }
  }
}
