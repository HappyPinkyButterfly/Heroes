import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {Power} from '../../models/power-model';

@Injectable({providedIn: 'root'})
export class PowerService {

  private http = inject(HttpClient);
  private readonly url = `${environment.url}/powers`;

  public getPowers(): Observable<Power[]> {
    return this.http.get<Power[]>(`${this.url}`);
  }
}
