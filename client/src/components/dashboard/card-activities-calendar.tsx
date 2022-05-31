import axios from 'axios';
import React, { useContext, useState } from 'react';
import { AuthContext } from '../../contexts/auth-context';
import { IActivity } from '../../models/activity';
import { IChild } from '../../models/child';
import { Helper } from '../../utils/helper';
import { Card } from '../common/card';
import './card-activities-calendar.scss';

interface IProps {
  activity: IActivity;
  child: IChild;
}

export const CardActivitiesCalendar: React.FC<IProps> = ({
  activity,
  child,
}) => {
  const { parent } = useContext(AuthContext);

  const { name, date, duration, price, id } = activity;

  const registered = child.activities.find((act) => act.id === id)
    ? true
    : false;

  const handleClick = async () => {
    if (!registered) {
      await axios.post(
        `http://localhost:8080/users/parents/${parent?.id}/children/${child.id}/activities/${id}`
      );
    } else {
      await axios.delete(
        `http://localhost:8080/users/parents/${parent?.id}/children/${child.id}/activities/${id}`
      );
    }
  };

  return (
    <Card
      className={`card-activities-calendar ${
        registered ? 'card-activities-calendar--registered' : ''
      }`}
      title={name}
      center
      onClick={handleClick}
    >
      <p>
        {Helper.getLitteralTime(date)} -{' '}
        {Helper.getLitteralTime(date + duration * 1000 * 60)}
      </p>
      <p>{Helper.getLitteralDate(date)}</p>
      <strong>{price} &euro;</strong>
    </Card>
  );
};
