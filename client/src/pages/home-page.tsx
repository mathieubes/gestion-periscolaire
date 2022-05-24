import { Avatar, Card, Tab, Tabs, Typography } from '@mui/material';
import React, { useContext } from 'react';
import { DashboardCell } from '../components/dashboard/dashboard-cell';
import { AuthContext } from '../contexts/auth-context';

import './home-page.scss';

export const HomePage: React.FC = () => {
  const { parent } = useContext(AuthContext);

  return (
    <>
      <h1>
        Bonjour {parent?.firstname} {parent?.lastname}
      </h1>

      <div className="home-page__dashboard">
        <DashboardCell title="Vos enfants sont inscrits à :">
          <ul>
            {parent?.children.map(({ firstname, lastname }, i) => (
              <li className="home-page__dashboard__item">
                <div>
                  {firstname} {lastname}
                </div>
                <div>:</div>
                <div>
                  {i + 1} activité{i + 1 > 1 && 's'}.
                </div>
              </li>
            ))}
          </ul>
        </DashboardCell>
        <DashboardCell title="Prévisions de vos dépenses du mois :">
          <Tabs aria-label="icon position tabs example">
            <Tab
              icon={
                <Avatar src="https://images.unsplash.com/photo-1652060491237-aaa9c2cbad1d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80" />
              }
              label="left"
            />
          </Tabs>
        </DashboardCell>
      </div>
    </>
  );
};
