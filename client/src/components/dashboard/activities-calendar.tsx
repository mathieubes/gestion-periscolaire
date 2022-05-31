import React from 'react';
import { Card } from '../common/card';

import './activities-calendar.scss';
import { CardActivitiesCalendar } from './card-activities-calendar';
import { IActivities } from './dashboard';

interface IProps {
  allActivities: IActivities[];
}

export const ActivitiesCalendar: React.FC<IProps> = ({ allActivities }) => {
  return (
    <div className="activities-calendar">
      {allActivities.map(({ date, activities }, i) => {
        return (
          <div key={i} className="activities-calendar__row">
            <div className="activities-calendar__row__date">{date}</div>
            {activities.map(({ title, start, end, date }, j) => (
              <CardActivitiesCalendar
                key={j}
                title={title}
                start={start}
                end={end}
                date={date}
              />
            ))}
          </div>
        );
      })}
    </div>
  );
};
