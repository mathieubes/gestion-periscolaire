import React, { useState } from 'react'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import { AuthContext } from './contexts/auth-context'
import { LoginPage } from './pages/login-page'

export const App = () => {
  const [authUser, setAuthUser] = useState(null)

  return (
    <AuthContext.Provider value={{ authUser, setAuthUser }}>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<AuthProtectedRoute />}>
            <Route path="mathieu" />
          </Route>

          <Route path="/login" element={<LoginPage />} />
        </Routes>
      </BrowserRouter>
    </AuthContext.Provider>
  )
}
