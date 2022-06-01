import React, { useState, useContext, useEffect } from 'react';
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

  const [remember, setRemember] = useState<boolean>(false);

  const [loading, setLoading] = useState<boolean>(false);
  const [hasErrors, setHasErrors] = useState<boolean>(false);

  useEffect(() => {
    const rem = localStorage.getItem('loggedUser');
    if (rem) logUser(rem);
  }, []);

  const logUser = async (body: string) => {
    const res = await axios.post<IParent>(
      'http://localhost:8080/users/parents/signin',
      JSON.parse(body)
    );
    if (res.data) {
      setParent!(res.data);
    }
    navigate('/');
  };

  const handleRememberMeButton = () => {
    setRemember((r) => !r);
  };

  const handleSignInButtonClick = async (e: any) => {
    e.preventDefault();
    setLoading(true);

    const body = {
      email,
      password,
    };

    axios
      .post<IParent>('http://localhost:8080/users/parents/signin', body)
      .then((res) => {
        if (res.data) {
          const parent = res.data;
          setParent!(parent);
          if (remember)
            localStorage.setItem('loggedUser', JSON.stringify(body));
          navigate('/');
        }
      })
      .catch(() => {
        setHasErrors(true);
      });

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
        <FormControlLabel
          style={{ alignSelf: 'start' }}
          control={
            <Checkbox
              size="small"
              checked={remember}
              onChange={handleRememberMeButton}
            />
          }
          label="Se souvenir de moi"
        />

        <div className="login-page__form__buttons">
          <LoadingButton type="submit" variant="contained" loading={loading}>
            Connexion
          </LoadingButton>
          <Button size="small" onClick={handleSignUpButtonClick}>
            Inscription
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
