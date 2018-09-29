
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

/**
 * Az foglalásokhoz tartozó lekérdezéseket tartalmazó ősosztály
 */
export abstract class ReservationService {

  constructor(
    private http: HttpClient
  ) { }

  protected abstract getAccepted(): Observable<any[]>;

  protected abstract findByUsername(username: string): Observable<any[]>;
}
