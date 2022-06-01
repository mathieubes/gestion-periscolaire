import axios from 'axios';
import React, { useContext, useState } from 'react';
import { AuthContext } from '../../contexts/auth-context';
import { IActivity } from '../../models/activity';
import { IChild } from '../../models/child';
import { Helper } from '../../utils/helper';
import { Card } from '../common/card';
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
} from '@mui/material';
import './card-activities-calendar.scss';

interface IProps {
  activity: IActivity;
  child: IChild;
  updateExpenses: () => void;
}

export const CardActivitiesCalendar: React.FC<IProps> = ({
  activity,
  child,
  updateExpenses,
}) => {
  const { parent } = useContext(AuthContext);

  const { name, date, duration, price, id } = activity;

  const registered = child.activities.find((act) => act.id === id)
    ? true
    : false;
  const [reg, setReg] = useState<boolean>(registered);

  const [open, setOpen] = useState<boolean>(false);

  const handleClose = () => {
    setOpen(false);
  };

  const handleSubmit = async () => {
    if (!registered) {
      await axios.post(
        `http://localhost:8080/users/parents/${parent?.id}/children/${child.id}/activities/${id}`
      );
      setReg(true);
      child.activities.push(activity);
    } else {
      await axios.delete(
        `http://localhost:8080/users/parents/${parent?.id}/children/${child.id}/activities/${id}`
      );
      setReg(false);
      child.activities = child.activities.filter((a) => a.id != activity.id);
    }

    updateExpenses();
    handleClose();
  };

  const handleClick = () => {
    setOpen(true);
  };

  return (
    <>
      <Card
        className={`card-activities-calendar ${
          reg ? 'card-activities-calendar--registered' : ''
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

      <Dialog open={open} onClose={handleClose} fullWidth={true}>
        <DialogTitle>
          Confirmation {reg ? 'de désinscription' : `d'inscription`} à
          l'activité
        </DialogTitle>
        <DialogContent>
          Êtes-vous sûr de vouloir {reg ? 'désinscrire' : 'inscrire'} votre
          enfant à cette activité ?
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Annuler</Button>

          <Button onClick={handleSubmit}>
            {reg ? 'Désinscrire' : 'Inscrire'} mon enfant
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
};
