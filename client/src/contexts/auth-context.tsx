import React, { useState, createContext } from 'react';
import { IParent } from '../models/parent';

interface IContext {
  parent?: IParent;
  setParent?: React.Dispatch<React.SetStateAction<IParent | undefined>>;
}

export const AuthContext = createContext<IContext>({});

interface IProps {
  children: React.ReactNode;
}

export const AuthContextProvider: React.FC<IProps> = ({ children }) => {
  const [parent, setParent] = useState<IParent>();

  return (
    <AuthContext.Provider value={{ parent, setParent }}>
      {children}
    </AuthContext.Provider>
  );
};
