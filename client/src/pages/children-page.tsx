import React, { useContext, useState } from 'react';
import { Card } from '../components/common/card';
import { AuthContext } from '../contexts/auth-context';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import './children-page.scss';
import {
  Button,
  Checkbox,
  Chip,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  MenuItem,
  OutlinedInput,
  Select,
  Stack,
} from '@mui/material';
import axios from 'axios';
import { Helper } from '../utils/helper';
import { IChild } from '../models/child';

export const ChildrenPage: React.FC = () => {
  const { parent } = useContext(AuthContext);

  const [selectedChild, setSelectedChild] = useState<number>(0);
  const [editMode, setEditMode] = useState<boolean>(false);

  const [firstname, setFirstname] = useState<string>();
  const [lastname, setLastname] = useState<string>();
  const [birthDate, setBirthDate] = useState<string>();
  const [gender, setGender] = useState<string>('FEMALE');
  const [dependent, setDependent] = useState<boolean>();

  const [open, setOpen] = useState<boolean>(false);

  const handleClose = () => {
    setOpen(false);
  };

  const handleChildRegister = async () => {
    const body = {
      lastname,
      firstname,
      birthDate,
      gender,
      dependent,
    };
    if (editMode) {
      const child = parent?.children[selectedChild];
      const res = await axios.post(
        `http://localhost:8080/users/parents/${parent?.id}/children/${child?.id}/update`,
        body
      );

      if (res.data) {
        parent!.children[selectedChild] = res.data;
      }
    } else {
      const res = await axios.post<IChild[]>(
        `http://localhost:8080/users/parents/${parent?.id}/children`,
        body
      );

      if (res.data) {
        parent!.children = res.data;
      }
    }

    handleClose();
  };

  const handleRemoveChild = async () => {
    const child = parent?.children[selectedChild];
    await axios.delete(
      `http://localhost:8080/users/parents/${parent?.id}/children/${child?.id}`
    );

    parent!.children = parent.children.filter((c) => c.id != child.id);

    handleClose();
  };

  const handleEditButton = (id: number) => {
    const child = parent?.children[id];

    setFirstname(child?.firstname);
    setLastname(child?.lastname);
    setBirthDate(Helper.toDateInput(child?.birthDate!));
    setGender(child?.gender!);
    setDependent(child?.dependent);

    setSelectedChild(id);
    setEditMode(true);

    setOpen(true);
  };

  return (
    <>
      <h1>Mes enfants</h1>

      <div className="children-page">
        {parent?.children.map(
          ({ firstname, lastname, birthDate, dependent: dp, gender }, i) => {
            return (
              <Card
                className={`children-page__child ${
                  gender === 'FEMALE' ? 'children-page__child--girl' : ''
                }`}
                onClick={() => handleEditButton(i)}
              >
                <h3>
                  {lastname} {firstname}
                </h3>
                <p>{Helper.getLitteralBirthDate(birthDate)}</p>

                <Stack direction="row">
                  {dp ? (
                    <Chip label="A charge" color="success" />
                  ) : (
                    <Chip label="Non à charge" color="error" />
                  )}
                </Stack>
              </Card>
            );
          }
        )}
        <Card className="children-page__add-child">
          <div onClick={() => setOpen(true)}>
            <AddCircleIcon />
            <h3>Ajouter un enfant</h3>
          </div>
        </Card>
      </div>

      <Dialog open={open} onClose={handleClose} fullWidth={true}>
        <DialogTitle>Ajouter un enfant</DialogTitle>
        <DialogContent>
          <Stack direction="column" gap="16px">
            <OutlinedInput
              type="text"
              placeholder="Nom"
              value={lastname}
              onChange={(e) => setLastname(e.target.value)}
            />
            <OutlinedInput
              type="text"
              placeholder="Prénom"
              value={firstname}
              onChange={(e) => setFirstname(e.target.value)}
            />
            <OutlinedInput
              type="date"
              value={birthDate}
              onChange={(e) => setBirthDate(e.target.value)}
            />
            <Select value={gender} onChange={(e) => setGender(e.target.value)}>
              <MenuItem value={'MALE'}>Garcon</MenuItem>
              <MenuItem value={'FEMALE'}>Fille</MenuItem>
            </Select>
            <div>
              <Checkbox
                value={dependent}
                onChange={() => setDependent((dpd) => !dpd)}
              />
              Enfant à charge
            </div>
          </Stack>
        </DialogContent>
        <DialogActions>
          {editMode && (
            <Button onClick={handleRemoveChild} color="error">
              Supprimer mon enfant
            </Button>
          )}

          <Button onClick={handleChildRegister}>
            {editMode ? 'Mettre à jour' : 'Ajouter'} mon enfant
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
};
