import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { AuthContextProvider } from './contexts/auth-context';
import { AuthProtectedRoute } from './components/routes/auth-protected-route';
import { LoginPage } from './pages/login-page';

export const App: React.FC = () => {
  return (
    <AuthContextProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<AuthProtectedRoute />}>
            <Route path="/" />
          </Route>

          <Route path="/login" element={<LoginPage />} />
        </Routes>
      </BrowserRouter>
    </AuthContextProvider>
  );
};
