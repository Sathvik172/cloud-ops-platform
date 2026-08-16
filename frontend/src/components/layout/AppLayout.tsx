import { NavLink, Outlet } from 'react-router-dom'

const navigation = [
  { to: '/', label: 'Dashboard' },
  { to: '/monitoring', label: 'Monitoring' },
  { to: '/logs', label: 'Logs' },
  { to: '/alerts', label: 'Alerts' },
  { to: '/settings', label: 'Settings' },
]

function AppLayout() {
  return (
    <div className="app-shell">
      <aside className="app-sidebar">
        <div>
          <h1>Cloud Ops Platform</h1>
          <p>Operations workspace</p>
        </div>
        <nav>
          {navigation.map((item) => (
            <NavLink
              key={item.to}
              to={item.to}
              className={({ isActive }) => (isActive ? 'nav-link active' : 'nav-link')}
            >
              {item.label}
            </NavLink>
          ))}
        </nav>
      </aside>

      <main className="app-main">
        <header className="app-header">
          <h2>Platform overview</h2>
          <NavLink to="/login" className="nav-link secondary">
            Login
          </NavLink>
        </header>
        <Outlet />
      </main>
    </div>
  )
}

export default AppLayout
