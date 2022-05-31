import { IActivity } from './activity';

export interface IChild {
  id: string;
  firstname: string;
  lastname: string;
  birthDate: number;
  gender: string;
  dependent: boolean;
  activities: IActivity[];
}
