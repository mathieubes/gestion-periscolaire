import { Tab, Tabs } from '@mui/material';
import React, { useContext, useState } from 'react';
import { AuthContext } from '../../contexts/auth-context';
import { IActivity } from '../../models/activity';
import { Card } from '../common/card';
import { ActivitiesCalendar } from './activities-calendar';
import './dashboard.scss';

interface IProps {
  activities: IActivity[];
  totalExpenses: number;
}

export const Dashboard: React.FC<IProps> = ({ activities, totalExpenses }) => {
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
            <Tab iconPosition="start" label={firstname} />
          ))}
        </Tabs>

        {children.length ? (
          <ActivitiesCalendar
            allActivities={activities}
            child={children[selectedChild]}
          />
        ) : (
          <h3>Vous n'avez pas d'enfants renseigné</h3>
        )}
      </Card>

      <Card title="Prévisions des dépenses du mois">
        {totalExpenses ? (
          <h2>
            Votre facture du mois sera de {Math.floor(totalExpenses)}&euro;
          </h2>
        ) : (
          <p>Vous n'avez pas de dépenses prévues</p>
        )}
      </Card>

      <Card title="Accéder à mon espace">titi</Card>
      <Card title="Gérer mes enfants">tutu</Card>
      <Card title="Réaliser une inscription">tutu</Card>
      <Card title="Consulter ma dernière facture">tutu</Card>
    </div>
  );
};
