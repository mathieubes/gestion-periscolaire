import { IChild } from './child';
import { IUser } from './user';

export interface IParent extends IUser {
  fiscalNumber: number;
  children: IChild[];
}
