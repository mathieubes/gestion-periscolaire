import React, { useContext, useState } from 'react';
import {
  Button,
  FormControlLabel,
  Checkbox,
  Typography,
  InputAdornment,
  OutlinedInput,
  Snackbar,
  Alert,
  Stack,
} from '@mui/material';
import EmailRoundedIcon from '@mui/icons-material/EmailRounded';
import PasswordRoundedIcon from '@mui/icons-material/PasswordRounded';
import WarningAmberIcon from '@mui/icons-material/WarningAmber';
import FamilyRestroomRoundedIcon from '@mui/icons-material/FamilyRestroomRounded';
import FaceRoundedIcon from '@mui/icons-material/FaceRounded';
import MyLocationRoundedIcon from '@mui/icons-material/MyLocationRounded';
import PhoneIphoneRoundedIcon from '@mui/icons-material/PhoneIphoneRounded';
import AccountBalanceWalletRoundedIcon from '@mui/icons-material/AccountBalanceWalletRounded';
import './login-page.scss';
import './informations-page.scss';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { AuthContext } from '../contexts/auth-context';

export const InformationsPage = () => {
  const { parent } = useContext(AuthContext);

  const logs = JSON.parse(localStorage.getItem('loggedUser')!);

  const navigate = useNavigate();

  const [firstname, setFirstname] = useState<string>(parent!.firstname);
  const [lastname, setLastname] = useState<string>(parent!.lastname);
  const [email, setEmail] = useState<string>(parent!.email);
  const [password, setPassword] = useState<string>(logs.password);
  const [confirmedPassword, setConfirmedPassword] = useState<string>(
    logs.password
  );
  const [address, setAddress] = useState<string>(parent!.address);
  const [phoneNumber, setPhoneNumber] = useState<string>(parent!.phoneNumber);
  const [annualIncome, setAnnualIncome] = useState<string>(
    parent!.annualIncome.toString()
  );

  const [infos, setInfos] = useState<boolean>();

  const handleSignUpButtonClick = async () => {
    const body = {
      lastname,
      firstname,
      email,
      password,
      address,
      phoneNumber,
    };

    axios.post(
      `http://localhost:8080/users/parents/${parent?.id}/update`,
      body
    );

    setInfos(true);
  };

  const handleUpdateCoef = async () => {
    axios.get(
      `http://localhost:8080/users/parents/${parent?.id}/fiscal?annualIncome=${annualIncome}`
    );
    setInfos(true);
  };

  return (
    <main className="login-page informations-page">
      <form onSubmit={handleSignUpButtonClick}>
        <Stack direction="column" gap="16px">
          <Typography variant="h4">Mes informations</Typography>
          <Stack direction="row" gap="16px">
            <OutlinedInput
              type="text"
              placeholder="Nom"
              startAdornment={
                <InputAdornment position="start">
                  <FamilyRestroomRoundedIcon />
                </InputAdornment>
              }
              value={lastname}
              onChange={(e) => setLastname(e.target.value)}
            />
            <OutlinedInput
              type="text"
              placeholder="Prénom"
              startAdornment={
                <InputAdornment position="start">
                  <FaceRoundedIcon />
                </InputAdornment>
              }
              value={firstname}
              onChange={(e) => setFirstname(e.target.value)}
            />
          </Stack>

          <OutlinedInput
            type="email"
            placeholder="Email"
            startAdornment={
              <InputAdornment position="start">
                <EmailRoundedIcon />
              </InputAdornment>
            }
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <OutlinedInput
            type="password"
            placeholder="Mot de passe"
            startAdornment={
              <InputAdornment position="start">
                <PasswordRoundedIcon />
              </InputAdornment>
            }
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <OutlinedInput
            type="password"
            placeholder="Confirmation mot de passe"
            startAdornment={
              <InputAdornment position="start">
                <PasswordRoundedIcon />
              </InputAdornment>
            }
            value={confirmedPassword}
            onChange={(e) => setConfirmedPassword(e.target.value)}
          />
          <OutlinedInput
            type="text"
            placeholder="Adresse"
            startAdornment={
              <InputAdornment position="start">
                <MyLocationRoundedIcon />
              </InputAdornment>
            }
            value={address}
            onChange={(e) => setAddress(e.target.value)}
          />
          <OutlinedInput
            type="tel"
            placeholder="Téléphone"
            startAdornment={
              <InputAdornment position="start">
                <PhoneIphoneRoundedIcon />
              </InputAdornment>
            }
            value={phoneNumber}
            onChange={(e) => setPhoneNumber(e.target.value)}
          />

          <div className="login-page__form__buttons">
            <Button variant="contained" onClick={handleSignUpButtonClick}>
              Mettre à jour mes informations
            </Button>
          </div>
        </Stack>
      </form>

      <form>
        <Stack gap="16px">
          <OutlinedInput
            type="number"
            placeholder="Revenus annuel"
            startAdornment={
              <InputAdornment position="start">
                <AccountBalanceWalletRoundedIcon />
              </InputAdornment>
            }
            value={annualIncome}
            onChange={(e) => setAnnualIncome(e.target.value)}
          />

          <div className="login-page__form__buttons">
            <Button variant="contained" onClick={handleUpdateCoef}>
              Mettre à jour mon coeficiant
            </Button>
          </div>
        </Stack>
      </form>
      <Snackbar
        open={infos}
        autoHideDuration={5000}
        onClose={() => setInfos(false)}
      >
        <Alert
          severity="success"
          sx={{ width: '100%' }}
          icon={<WarningAmberIcon />}
        >
          Changements pris en compte.
        </Alert>
      </Snackbar>
    </main>
  );
};
