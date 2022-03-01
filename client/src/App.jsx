import React from 'react';
import { Routes, Route } from 'react-router-dom';
import { UsersPage } from './pages/UsersPage';

export const App = () => {
  return <Routes><Route path="users" element={<UsersPage />} /></Routes>;
};
