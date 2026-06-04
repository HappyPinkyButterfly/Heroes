import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Mission} from '../../models/mission-model';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class MissionService {

  private http = inject(HttpClient);

  getMissionsByHero(heroId: number): Observable<Mission[]> {
    return this.http.get<Mission[]>(
      `${environment.url}/heroes/${heroId}/missions`
    );
  }

  createMission(heroId: number, mission: Partial<Mission>): Observable<Mission> {
    return this.http.post<Mission>(
      `${environment.url}/heroes/${heroId}/missions`,
      mission
    );
  }
}
