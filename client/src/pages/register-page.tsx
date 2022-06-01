import React, { useState } from 'react';
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
import './login-page.scss';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

export const RegisterPage = () => {
  const navigate = useNavigate();

  const [firstname, setFirstname] = useState<string>();
  const [lastname, setLastname] = useState<string>();
  const [email, setEmail] = useState<string>();
  const [password, setPassword] = useState<string>();
  const [confirmedPassword, setConfirmedPassword] = useState<string>();
  const [address, setAddress] = useState<string>();
  const [phoneNumber, setPhoneNumber] = useState<string>();

  const [hasErrors, setHasErrors] = useState<boolean>();

  const handleSignUpButtonClick = async () => {
    const body = {
      lastname,
      firstname,
      email,
      password,
      address,
      phoneNumber,
    };

    axios.post('http://localhost:8080/users/parents', body);
  };

  return (
    <main className="login-page">
      <form onSubmit={handleSignUpButtonClick}>
        <Stack direction="column" gap="16px">
          <Typography variant="h4">Inscription</Typography>
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
              error={hasErrors}
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
              error={hasErrors}
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
            error={hasErrors}
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
            error={hasErrors}
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
            error={hasErrors}
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
            error={hasErrors}
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
            error={hasErrors}
          />

          <div className="login-page__form__buttons">
            <Button variant="contained" onClick={handleSignUpButtonClick}>
              S'inscrire
            </Button>
            <Button onClick={() => navigate('/login')}>
              J'ai déjà un compte
            </Button>
          </div>
        </Stack>
      </form>
      <Snackbar
        open={hasErrors}
        autoHideDuration={5000}
        onClose={() => setHasErrors(false)}
      >
        <Alert
          severity="error"
          sx={{ width: '100%' }}
          icon={<WarningAmberIcon />}
        >
          Email ou mot de passe incorrects.
        </Alert>
      </Snackbar>
    </main>
  );
};
