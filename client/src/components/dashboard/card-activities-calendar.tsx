import React from 'react';
import { Card } from '../common/card';
import './card-activities-calendar.scss';

interface IProps {
  title: string;
  start: string;
  end: string;
  date: string;
}

export const CardActivitiesCalendar: React.FC<IProps> = ({
  title,
  start,
  end,
  date,
}) => {
  return (
    <Card className="card-activities-calendar" title={title} center>
      <p>
        {start} - {end}
      </p>
      <p>{date}</p>
    </Card>
  );
};
