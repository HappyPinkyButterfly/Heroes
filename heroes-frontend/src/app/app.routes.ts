import { Routes } from '@angular/router';
import {DashboardComponent} from './features/components/dashboard/dashboard.component';
import {HeroListComponent} from './features/components/hero-list/hero-list.component';
import {HeroDetailComponent} from './features/components/hero-detail/hero-detail.component';
import {HeroFormComponent} from './features/components/hero-form/hero-form.component';

export const routes: Routes = [
  {path: '', redirectTo: 'dashboard', pathMatch: 'full'},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'heroes', component: HeroListComponent},
  {path: 'heroes/new', component: HeroFormComponent},
  {path: 'heroes/:id', component: HeroDetailComponent },
];
