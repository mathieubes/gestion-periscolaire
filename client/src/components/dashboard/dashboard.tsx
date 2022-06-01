import { ConstructionOutlined } from '@mui/icons-material';
import { Tab, Tabs, Button } from '@mui/material';
import axios from 'axios';
import React, { useContext, useEffect, useState } from 'react';
import { AuthContext } from '../../contexts/auth-context';
import { IActivity } from '../../models/activity';
import { Card } from '../common/card';
import { ActivitiesCalendar } from './activities-calendar';
import './dashboard.scss';

interface IProps {
  activities: IActivity[];
  totalExpenses: number;
  updateExpenses: () => void;
}

export const Dashboard: React.FC<IProps> = ({
  activities,
  totalExpenses,
  updateExpenses,
}) => {
  const { parent } = useContext(AuthContext);
  const { children } = parent!;

  const [selectedChild, setSelectedChild] = useState<number>(0);
  const [coef, setCoef] = useState<number>(0);

  const handleChange = (event: React.SyntheticEvent, newValue: number) => {
    setSelectedChild(newValue);
  };

  const updateCoef = async () => {
    const res = await axios.get(
      `http://localhost:8080/users/parents/${parent.id}/fiscal?annualIncome=${parent.annualIncome}`
    );

    setCoef(res.data);
    parent.coefNumber = res.data;
  };

  useEffect(() => {
    updateCoef();
  });

  interface TabPanelProps {
    children?: React.ReactNode;
    index: number;
    value: number;
  }

  function TabPanel(props: TabPanelProps) {
    const { children, value, index, ...other } = props;

    return (
      <div hidden={value !== index} {...other}>
        {value === index && <>{children}</>}
      </div>
    );
  }

  return (
    <div className="dashboard">
      <Card title="Activités à venir">
        <Tabs value={selectedChild} onChange={handleChange}>
          {children.map(({ firstname }) => (
            <Tab iconPosition="start" label={firstname} />
          ))}
        </Tabs>

        {children.map((child, i) => (
          <TabPanel key={i} value={selectedChild} index={i}>
            <ActivitiesCalendar
              allActivities={activities}
              child={child}
              updateExpenses={updateExpenses}
            />
          </TabPanel>
        ))}

        {children.length === 0 && <h3>Vous n'avez pas d'enfants renseigné</h3>}
      </Card>

      <Card title="Prévisions des dépenses du mois" center>
        {totalExpenses ? (
          <p>
            {totalExpenses === -1 ? (
              `Renseignez d'abord vos revenus annuels dans vos informations`
            ) : (
              <>
                Votre facture du mois sera de {Math.floor(totalExpenses)}&euro;
                <p>(coef: {coef}% de la somme)</p>
              </>
            )}
          </p>
        ) : (
          <p>Vous n'avez pas de dépenses prévues</p>
        )}
      </Card>

      <Card title="Consulter ma facture">Prochainement...</Card>
    </div>
  );
};
