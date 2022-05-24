import React from 'react';
import { Outlet } from 'react-router-dom';
import { Navbar } from './navbar/navbar';

import './layout.scss';

export const Layout: React.FC = () => {
  return (
    <div className="layout">
      <Navbar />
      <div className="layout__content">
        <Outlet />
      </div>
    </div>
  );
};
