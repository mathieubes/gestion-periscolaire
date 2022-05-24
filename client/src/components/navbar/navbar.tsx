import React, { useContext } from 'react';
import {
  Avatar,
  IconButton,
  ListItemIcon,
  ListItemText,
  Menu,
  MenuItem,
} from '@mui/material';
import EscalatorWarningIcon from '@mui/icons-material/EscalatorWarning';
import SportsBasketballIcon from '@mui/icons-material/SportsBasketball';
import DiningIcon from '@mui/icons-material/Dining';
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
    handleClose();
  };

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

      <IconButton onClick={handleClick}>
        <Avatar>{parent?.firstname[0]}</Avatar>
      </IconButton>
      <Menu
        id="basic-menu"
        anchorEl={menuEl}
        open={open}
        onClose={handleClose}
        MenuListProps={{
          'aria-labelledby': 'basic-button',
        }}
        anchorOrigin={{
          vertical: 'center',
          horizontal: 'right',
        }}
        transformOrigin={{
          vertical: 'center',
          horizontal: 'left',
        }}
      >
        <MenuItem onClick={handleLogout}>
          <ListItemIcon>
            <PowerSettingsNewRoundedIcon />
          </ListItemIcon>
          <ListItemText>Se deconnecter</ListItemText>
        </MenuItem>
      </Menu>
    </div>
  );
};
