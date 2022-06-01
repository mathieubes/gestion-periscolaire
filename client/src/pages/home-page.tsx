import axios from 'axios';
import React, { useContext, useEffect, useState } from 'react';
import { Dashboard } from '../components/dashboard/dashboard';
import { AuthContext } from '../contexts/auth-context';
import { IActivity } from '../models/activity';

import './home-page.scss';

export const HomePage: React.FC = () => {
  const { parent } = useContext(AuthContext);

  const [activities, setActivities] = useState<IActivity[]>([]);
  const [totalExpenses, setTotalExpenses] = useState<number>(0);

  useEffect(() => {
    getActvities();
    getExpenses();
  }, []);

  const getActvities = async () => {
    const res = await axios.get<IActivity[]>(
      'http://localhost:8080/activities'
    );

    if (res.data) {
      setActivities(res.data);
    }
  };

  const getExpenses = async () => {
    const res = await axios.get(
      `http://localhost:8080/users/parents/${parent?.id}/expenses`
    );

    setTotalExpenses(res.data);
  };

  return (
    <>
      <h1>
        Bonjour {parent?.firstname} {parent?.lastname}
      </h1>

      <Dashboard
        activities={activities}
        totalExpenses={totalExpenses}
        updateExpenses={getExpenses}
      />
    </>
  );
};
