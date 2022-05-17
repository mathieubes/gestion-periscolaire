import React, { useState, useContext } from 'react';
import {
  Button,
  FormControlLabel,
  Checkbox,
  Typography,
  InputAdornment,
  OutlinedInput,
  Snackbar,
  Alert,
} from '@mui/material';
import EmailRoundedIcon from '@mui/icons-material/EmailRounded';
import PasswordRoundedIcon from '@mui/icons-material/PasswordRounded';
import WarningAmberIcon from '@mui/icons-material/WarningAmber';
import './login-page.scss';
import { LoadingButton } from '@mui/lab';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { AuthContext } from '../contexts/auth-context';
import { IUser } from '../models/user';
import { IParent } from '../models/parent';

export const LoginPage: React.FC = () => {
  const navigate = useNavigate();
  const { setParent } = useContext(AuthContext);

  const [email, setEmail] = useState<string>('');
  const [password, setPassword] = useState<string>('');

  const [loading, setLoading] = useState<boolean>(false);
  const [hasErrors, setHasErrors] = useState<boolean>(false);

  const handleSignInButtonClick = async (e: any) => {
    e.preventDefault();
    setLoading(true);

    const body = {
      email,
      password,
    };
    const res = await axios.post<IParent>(
      'http://localhost:8080/users/parents/signin',
      body
    );
    if (res.data) {
      setParent!(res.data);
      localStorage.setItem('loggedUser', JSON.stringify(res.data));
      navigate('/');
    } else setHasErrors(true);

    setLoading(false);
  };

  const handleSignUpButtonClick = () => {
    navigate('/register');
  };

  return (
    <main className="login-page">
      <form className="login-page__form" onSubmit={handleSignInButtonClick}>
        <Typography variant="h4">Gestion périscolaire</Typography>
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
          placeholder="Password"
          startAdornment={
            <InputAdornment position="start">
              <PasswordRoundedIcon />
            </InputAdornment>
          }
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          error={hasErrors}
        />
        <FormControlLabel
          style={{ alignSelf: 'start' }}
          control={<Checkbox size="small" />}
          label="Remember me"
        />

        <div className="login-page__form__buttons">
          <LoadingButton type="submit" variant="contained" loading={loading}>
            Sign in
          </LoadingButton>
          <Button size="small" onClick={handleSignUpButtonClick}>
            Sign up
          </Button>
        </div>
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
