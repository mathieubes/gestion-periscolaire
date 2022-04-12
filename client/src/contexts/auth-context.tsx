import React, { useState, createContext } from 'react';
import { IUser } from '../models/user';

interface IContext {
  user?: IUser;
  setUser?: React.Dispatch<React.SetStateAction<IUser | undefined>>;
}

export const AuthContext = createContext<IContext>({});

interface IProps {
  children: React.ReactNode;
}

export const AuthContextProvider: React.FC<IProps> = ({ children }) => {
  const [user, setUser] = useState<IUser>();

  return (
    <AuthContext.Provider value={{ user, setUser }}>
      {children}
    </AuthContext.Provider>
  );
};
