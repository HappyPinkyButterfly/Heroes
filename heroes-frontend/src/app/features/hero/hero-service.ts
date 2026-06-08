import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {Hero} from '../../models/hero-model';

@Injectable({providedIn: 'root'})
export class HeroService {

  private http = inject(HttpClient);
  private readonly url = `${environment.url}/heroes`;

  getHeroes(): Observable<Hero[]> {
    return this.http.get<Hero[]>(`${this.url}`);
  }

  getHeroById(id: number): Observable<Hero> {
    return this.http.get<Hero>(`${this.url}/${id}`);
  }

  createHero(hero: Partial<Hero>): Observable<Hero> {
    return this.http.post<Hero>(`${this.url}/${hero.id}`, hero);
  }

  updateHero(id: number, hero: Partial<Hero>): Observable<Hero> {
    return this.http.put<Hero>(`${this.url}/${id}`, hero);
  }
  deleteHero(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
