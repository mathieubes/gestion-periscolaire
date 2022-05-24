import { Card } from '@mui/material';
import React from 'react';
import './dashboard-cell.scss';

interface IProps {
  title: string;
  children: React.ReactNode;
}

export const DashboardCell: React.FC<IProps> = ({ title, children }) => {
  return (
    <Card className="dashboard-cell">
      <div className="dashboard-cell__title">{title}</div>
      <div className="dashboard-cell__content">{children}</div>
    </Card>
  );
};
