import React, { useContext } from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { AuthContext } from '../../contexts/auth-context';
import { Layout } from '../layout';

const REDIRECTION_PATH = '/login';

export const AuthProtectedRoute: React.FC = () => {
  const { parent } = useContext(AuthContext);
  if (!parent) return <Navigate to={REDIRECTION_PATH} />;
  return <Layout />;
};
