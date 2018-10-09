export class TextUtils {
  /**
   * Sortörések hozzáadása a szöveghez:
   * @param text A szöveg
   */
  public static addBreaks(text: string): string {
    return text.replace(new RegExp("\n", "g"), "<br />");
  }
}
