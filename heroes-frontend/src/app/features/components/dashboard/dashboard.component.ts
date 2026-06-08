import {Component, OnInit} from '@angular/core';
import {Hero} from '../../../models/hero-model';
import {HeroService} from '../../hero/hero-service';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    RouterLink,
  ],
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent implements OnInit {

  heroes: Hero[] = [];

  constructor(
    private heroService: HeroService,
  ) {}

  ngOnInit() {
    this.heroService.getHeroes().subscribe({
      next: data => {
        console.log('Heroes:', data);
        this.heroes = data.slice(0, 5);
        },
      error: error => console.log(error),
    })
  }


}
