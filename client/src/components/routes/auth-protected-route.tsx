import React, { useContext } from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { AuthContext } from '../../contexts/auth-context';

const REDIRECTION_PATH = '/login';

export const AuthProtectedRoute: React.FC = () => {
  const { user } = useContext(AuthContext);
  console.log(user);
  if (!user) return <Navigate to={REDIRECTION_PATH} />;
  return <Outlet />;
};
