import {Power} from './power-model';

export interface Hero {
  id: number;
  name: string;
  description?: string;
  status: string;
  createdAt?: Date;
  userId?: string;
  powers?: Power[];
}
