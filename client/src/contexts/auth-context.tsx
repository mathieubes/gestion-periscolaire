import React, { useState, createContext, useEffect } from 'react';
import { IParent } from '../models/parent';

interface IContext {
  parent?: IParent;
  setParent?: React.Dispatch<React.SetStateAction<IParent>>;
}

export const AuthContext = createContext<IContext>({});

interface IProps {
  children: React.ReactNode;
}

export const AuthContextProvider: React.FC<IProps> = ({ children }) => {
  const user = JSON.parse(
    localStorage.getItem('loggedUser') || '{}'
  ) as IParent;

  const [parent, setParent] = useState<IParent>(user);

  return (
    <AuthContext.Provider value={{ parent, setParent }}>
      {children}
    </AuthContext.Provider>
  );
};
