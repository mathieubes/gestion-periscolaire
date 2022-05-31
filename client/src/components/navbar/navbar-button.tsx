import { IconButton, Tooltip } from '@mui/material';
import React from 'react';
import { Link } from 'react-router-dom';

interface IProps {
  title: string;
  icon: React.ReactNode;
  to?: string;
  onClick?: () => void;
}

export const NavbarButton: React.FC<IProps> = ({
  title,
  icon,
  to,
  onClick,
}) => {
  return (
    <Link
      to={to || ''}
      onClick={() => {
        if (onClick) onClick();
      }}
    >
      <Tooltip title={title} placement="right">
        <IconButton>{icon}</IconButton>
      </Tooltip>
    </Link>
  );
};
