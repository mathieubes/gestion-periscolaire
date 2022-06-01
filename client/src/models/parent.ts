import { IChild } from './child';
import { IUser } from './user';

export interface IParent extends IUser {
  coefNumber: number;
  annualIncome: number;
  children: IChild[];
}
