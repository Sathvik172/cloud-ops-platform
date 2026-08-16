import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import AppLayout from '../components/layout/AppLayout'
import DashboardPage from '../pages/Dashboard'
import LoginPage from '../pages/Login'
import MonitoringPage from '../pages/Monitoring'
import LogsPage from '../pages/Logs'
import AlertsPage from '../pages/Alerts'
import SettingsPage from '../pages/Settings'

const router = createBrowserRouter([
  {
    path: '/',
    element: <AppLayout />,
    children: [
      { index: true, element: <DashboardPage /> },
      { path: 'monitoring', element: <MonitoringPage /> },
      { path: 'logs', element: <LogsPage /> },
      { path: 'alerts', element: <AlertsPage /> },
      { path: 'settings', element: <SettingsPage /> },
    ],
  },
  { path: '/login', element: <LoginPage /> },
])

function AppRoutes() {
  return <RouterProvider router={router} />
}

export default AppRoutes
