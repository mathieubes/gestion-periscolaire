import React from 'react';
import { IActivity } from '../../models/activity';
import { IChild } from '../../models/child';
import { Card } from '../common/card';

import './activities-calendar.scss';
import { CardActivitiesCalendar } from './card-activities-calendar';

interface IProps {
  allActivities: IActivity[];
  child: IChild;
  updateExpenses: () => void;
}

export const ActivitiesCalendar: React.FC<IProps> = ({
  allActivities,
  child,
  updateExpenses,
}) => {
  return (
    <div className="activities-calendar">
      {allActivities.map((activity, i) => {
        return (
          <div key={i} className="activities-calendar__row">
            <CardActivitiesCalendar
              activity={activity}
              child={child}
              updateExpenses={updateExpenses}
            />
          </div>
        );
      })}
    </div>
  );
};
