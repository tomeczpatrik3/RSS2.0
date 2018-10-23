import { Pipe, PipeTransform } from "@angular/core";

/**
 * Példa a használatra: 
 * {{ value | empty }}
 * {{ value | empty:"anotherValue" }}
 */
@Pipe({ name: "empty" })
export class EmptyPipe implements PipeTransform {
  transform(value: string, substitute: string): string {
    if (!value || value === "") {
      return substitute ? substitute : "-";
    } else {
      return value;
    }
  }
}
