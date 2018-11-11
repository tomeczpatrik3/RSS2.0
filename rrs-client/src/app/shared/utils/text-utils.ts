/**
 * A szövegekkel kapcsolatos műveletekért felelős osztály
 */
export class TextUtils {
  /**
   * Sortörések hozzáadása a szöveghez:
   * @param text A szöveg
   */
  public static addBreaks(text: any): any {
    if (typeof text === "string" || text instanceof String) {
      return text.replace(new RegExp("\n", "g"), "<br />");
    } else {
      console.log(text);
      return "Hiba történt!";
    }
  }
}
