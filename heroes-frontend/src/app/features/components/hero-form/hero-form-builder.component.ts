import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {Power} from '../../../models/power-model';

export type HeroFormControls = {
  id: FormControl<number>;
  name: FormControl<string>;
  description: FormControl<string | null>;
  status: FormControl<'active'|'inactive'>;
  userId: FormControl<string>;
  powers: FormControl<Power[]>;
}

export type HeroFormGroup = FormGroup<HeroFormControls>;

export function buildHeroForm(): HeroFormGroup {
  return new FormGroup<HeroFormControls>({
    id: new FormControl<number>(0,{
      nonNullable:true
    }),
    name: new FormControl<string>('',{
      nonNullable:true
    }),
    description: new FormControl<string | null>(null),
    status: new FormControl<'active'|'inactive'>('active',{
      nonNullable:true
    }),
    userId: new FormControl<string>('',
      {
        nonNullable:true
      }),
    powers: new FormControl<Power[]>([],{
      nonNullable:true
    }),
  })
}
