export type AppTheme = 'dark' | 'light'

export interface ServiceStatus {
  name: string
  status: 'healthy' | 'warning' | 'critical'
}
