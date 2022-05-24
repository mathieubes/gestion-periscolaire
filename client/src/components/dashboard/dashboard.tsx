import { Tab, Tabs } from '@mui/material';
import React, { useContext, useState } from 'react';
import { AuthContext } from '../../contexts/auth-context';
import { Card } from '../common/card';
import { ActivitiesCalendar } from './activities-calendar';
import './dashboard.scss';

const TMP_CHILD_IMG =
  'https://images.unsplash.com/photo-1629783509182-68c8c190e952?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80';

interface IActivity {
  title: string;
  start: string;
  end: string;
  date: string;
}
export interface IActivities {
  date: string;
  activities: IActivity[];
}

const TMP_ACTIVITIES: IActivities[] = [
  {
    date: '05/08',
    activities: [
      {
        title: 'Football',
        start: '14h50',
        end: '15h30',
        date: 'Jeudi 5 aout',
      },
      {
        title: 'Football',
        start: '15h50',
        end: '16h30',
        date: 'Jeudi 5 aout',
      },
      {
        title: 'Danse',
        start: '16h50',
        end: '17h30',
        date: 'Jeudi 5 aout',
      },
    ],
  },
  {
    date: '06/08',
    activities: [
      {
        title: 'Football',
        start: '14h50',
        end: '15h30',
        date: 'Vendredi 6 aout',
      },
      {
        title: 'Football',
        start: '15h50',
        end: '16h30',
        date: 'Vendredi 6 aout',
      },
      {
        title: 'Danse',
        start: '16h50',
        end: '17h30',
        date: 'Vendredi 6 aout',
      },
    ],
  },
];

export const Dashboard: React.FC = () => {
  const { parent } = useContext(AuthContext);
  const { children } = parent!;

  const [selectedChild, setSelectedChild] = useState<number>(0);

  const handleChange = (event: React.SyntheticEvent, newValue: number) => {
    setSelectedChild(newValue);
  };

  return (
    <div className="dashboard">
      <Card title="Activités à venir">
        <Tabs value={selectedChild} onChange={handleChange}>
          {children.map(({ firstname }) => (
            <Tab
              icon={
                <img
                  style={{ width: 32, height: 32, borderRadius: 16 }}
                  src={TMP_CHILD_IMG}
                />
              }
              iconPosition="start"
              label={firstname}
            />
          ))}
        </Tabs>

        <ActivitiesCalendar allActivities={TMP_ACTIVITIES} />
      </Card>

      <Card title="Prévisions des dépenses du mois">tata</Card>
      <Card title="Accéder à mon espace">titi</Card>
      <Card title="Gérer mes enfants">tutu</Card>
      <Card title="Réaliser une inscription">tutu</Card>
      <Card title="Consulter ma dernière facture">tutu</Card>
    </div>
  );
};
