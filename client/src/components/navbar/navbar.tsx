import React, { useContext } from 'react';
import {
  Avatar,
  Dialog,
  DialogContent,
  DialogTitle,
  IconButton,
  ListItemIcon,
  ListItemText,
  Menu,
  MenuItem,
  OutlinedInput,
  Stack,
} from '@mui/material';
import EscalatorWarningIcon from '@mui/icons-material/EscalatorWarning';
import SportsBasketballIcon from '@mui/icons-material/SportsBasketball';
import DiningIcon from '@mui/icons-material/Dining';
import SettingsIcon from '@mui/icons-material/Settings';
import HomeRoundedIcon from '@mui/icons-material/HomeRounded';
import { AuthContext } from '../../contexts/auth-context';
import { NavbarButton } from './navbar-button';
import PowerSettingsNewRoundedIcon from '@mui/icons-material/PowerSettingsNewRounded';

import './navbar.scss';
import { IParent } from '../../models/parent';

export const Navbar: React.FC = () => {
  const { parent, setParent } = useContext(AuthContext);

  const [menuEl, setMenuEl] = React.useState<null | HTMLElement>(null);
  const open = Boolean(menuEl);
  const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
    setMenuEl(event.currentTarget);
  };
  const handleClose = () => {
    setMenuEl(null);
  };

  const handleLogout = () => {
    setParent!({} as IParent);
    localStorage.removeItem('loggedUser');
    handleClose();
  };

  return (
    <>
      <div className="navbar">
        <nav>
          <NavbarButton title="Dashboard" icon={<HomeRoundedIcon />} to="/" />
          <NavbarButton
            title="Mes enfants"
            icon={<EscalatorWarningIcon />}
            to="/children"
          />
        </nav>

        <nav>
          <NavbarButton
            title="Modifier mes informations"
            icon={<SettingsIcon />}
            to="/informations"
          />

          <NavbarButton
            title="Se déconnecter"
            icon={<PowerSettingsNewRoundedIcon />}
            onClick={handleLogout}
          />
        </nav>
      </div>
    </>
  );
};
