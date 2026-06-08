import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import {Hero} from '../../../models/hero-model';
import {Mission} from '../../../models/mission-model';
import {HeroService} from '../../hero/hero-service';
import {MissionService} from '../../mission/mission-service';

@Component({
  selector: 'app-hero-detail',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './hero-detail.component.html',
})
export class HeroDetailComponent implements OnInit {

  hero?: Hero;
  missions: Mission[] = [];
  loading = true;
  error = '';

  constructor(
    private route: ActivatedRoute,
    private heroService: HeroService,
    private missionService: MissionService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.heroService.getHeroById(id).subscribe({
      next: (hero) => {
        this.hero = hero;
        this.loading = false;
      },
      error: () => {
        this.error = 'Hero not found.';
        this.loading = false;
      }
    });

    this.missionService.getMissionsByHero(id).subscribe({
      next: (missions) => this.missions = missions,
      error: (err) => console.error(err)
    });
  }
}
