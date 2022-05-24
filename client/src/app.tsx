import React, { Children } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { AuthContextProvider } from './contexts/auth-context';
import { AuthProtectedRoute } from './components/routes/auth-protected-route';
import { LoginPage } from './pages/login-page';
import { RegisterPage } from './pages/register-page';
import { HomePage } from './pages/home-page';
import { ChildrenPage } from './pages/children-page';
import { ActivitiesPage } from './pages/activites-page';
import { CafeteriaPage } from './pages/cafetera-page';

export const App: React.FC = () => {
  return (
    <AuthContextProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<AuthProtectedRoute />}>
            <Route path="/" element={<HomePage />} />
            <Route path="/children" element={<ChildrenPage />} />
            <Route path="/actvities" element={<ActivitiesPage />} />
            <Route path="/cafeteria" element={<CafeteriaPage />} />
          </Route>

          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
        </Routes>
      </BrowserRouter>
    </AuthContextProvider>
  );
};
