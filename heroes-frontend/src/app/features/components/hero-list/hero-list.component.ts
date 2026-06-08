import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import {Hero} from '../../../models/hero-model';
import {HeroService} from '../../hero/hero-service';

;

@Component({
  selector: 'app-hero-list',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './hero-list.component.html',
})
export class HeroListComponent implements OnInit {

  heroes: Hero[] = [];

  constructor(private heroService: HeroService) {}

  ngOnInit(): void {
    this.heroService.getHeroes().subscribe({
      next: (heroes) => this.heroes = heroes,
      error: (err) => console.error(err)
    });
  }
}
