import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {HeroService} from '../../hero/hero-service';
import {buildHeroForm, HeroFormControls, HeroFormGroup} from './hero-form-builder.component';

@Component({
  selector: 'app-hero-form',
  templateUrl: './hero-form.component.html',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterLink
  ],
})
export class HeroFormComponent {

  constructor(
    private heroService: HeroService,
    private router: Router
  ) {
  }

  form: HeroFormGroup = buildHeroForm();

  submit(): void {
    if (this.form.invalid) {
      alert('Form invalid');
      this.form.markAllAsTouched();
      return;
    }

    const value = this.form.getRawValue();

    this.heroService.createHero({
      name: value.name,
      description: value.description ?? undefined,
      status: value.status ?? 'active',
      userId: value.userId,
    }).subscribe(async hero => {
      await this.router.navigate(['/heroes', hero.id]);
    })
  }





}
