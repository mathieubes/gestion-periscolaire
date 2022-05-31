import { IActivity } from './activity';

export interface IChild {
  id: string;
  firstname: string;
  lastname: string;
  birthDate: Date;
  gender: number;
  dependent: boolean;
  activities: IActivity[];
}
