type ButtonProps = {
  children: React.ReactNode
  variant?: 'primary' | 'secondary'
  onClick?: () => void
}

function Button({ children, variant = 'primary', onClick }: ButtonProps) {
  return (
    <button className={`button ${variant}`} onClick={onClick} type="button">
      {children}
    </button>
  )
}

export default Button
