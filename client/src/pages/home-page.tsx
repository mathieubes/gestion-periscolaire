import React, { useContext } from 'react';
import { Dashboard } from '../components/dashboard/dashboard';
import { AuthContext } from '../contexts/auth-context';

import './home-page.scss';

export const HomePage: React.FC = () => {
  const { parent } = useContext(AuthContext);

  return (
    <>
      <h1>
        Bonjour {parent?.firstname} {parent?.lastname}
      </h1>

      <Dashboard />
    </>
  );
};
