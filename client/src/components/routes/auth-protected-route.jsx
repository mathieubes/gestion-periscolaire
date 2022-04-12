import React, { useContext } from 'react'
import { Navigate, Outlet } from 'react-router-dom'
import { AuthContext } from '../../contexts/auth-context'

const redirectionPath = '/login'

export const AuthProtectedRoute = () => {
  const { authUser } = useContext(AuthContext)

  if (!authUser) return <Navigate to={redirectionPath} />

  return <Outlet />
}
