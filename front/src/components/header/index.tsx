export default function Header() {
    const currentPath = window.location.pathname;
  
    return (
      <header className="bg-dark p-3">
        <nav className="navbar navbar-expand-lg navbar-dark container">
          <a
            href="/"
            className={`nav-link d-inline-block px-3 ${
              currentPath === "/"
                ? "fw-bold text-white border-bottom border-white"
                : "text-white-50"
            }`}
          >
            Home
          </a>
          <a
            href="/usuarios"
            className={`nav-link d-inline-block px-3 ${
              currentPath === "/usuarios"
                ? "fw-bold text-white border-bottom border-white"
                : "text-white-50"
            }`}
          >
            Usuarios
          </a>
        </nav>
      </header>
    );
  }