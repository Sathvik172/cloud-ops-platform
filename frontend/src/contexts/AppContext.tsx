import { createContext, useContext, useMemo, useState } from 'react'

type AppContextValue = {
  theme: 'dark' | 'light'
  setTheme: (theme: 'dark' | 'light') => void
}

const AppContext = createContext<AppContextValue | undefined>(undefined)

export function AppProvider({ children }: { children: React.ReactNode }) {
  const [theme, setTheme] = useState<'dark' | 'light'>('dark')

  const value = useMemo(() => ({ theme, setTheme }), [theme])

  return <AppContext.Provider value={value}>{children}</AppContext.Provider>
}

export function useAppContext() {
  const context = useContext(AppContext)
  if (!context) {
    throw new Error('useAppContext must be used within an AppProvider')
  }
  return context
}
