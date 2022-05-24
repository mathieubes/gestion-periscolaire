import React, { useContext } from 'react';
import { Avatar, IconButton } from '@mui/material';
import EscalatorWarningIcon from '@mui/icons-material/EscalatorWarning';
import SportsBasketballIcon from '@mui/icons-material/SportsBasketball';
import DiningIcon from '@mui/icons-material/Dining';
import HomeRoundedIcon from '@mui/icons-material/HomeRounded';
import { AuthContext } from '../../contexts/auth-context';
import { NavbarButton } from './navbar-button';

import './navbar.scss';

export const Navbar: React.FC = () => {
  const { parent } = useContext(AuthContext);

  return (
    <div className="navbar">
      <nav>
        <NavbarButton title="Home" icon={<HomeRoundedIcon />} to="/" />
        <NavbarButton
          title="My children"
          icon={<EscalatorWarningIcon />}
          to="/children"
        />
        <NavbarButton
          title="Activities"
          icon={<SportsBasketballIcon />}
          to="/actvities"
        />
        <NavbarButton title="Cafeteria" icon={<DiningIcon />} to="/cafeteria" />
      </nav>

      <IconButton>
        <Avatar>{parent?.firstname[0]}</Avatar>
      </IconButton>
    </div>
  );
};
