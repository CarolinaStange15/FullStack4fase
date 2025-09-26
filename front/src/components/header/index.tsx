import { Link } from "react-router-dom";

export default function Header() {
    const currentPath = window.location.pathname;
  
    return (
      <header className="bg-dark p-3">
        <nav className="navbar navbar-expand-lg navbar-dark container">
          <a
            href="/"
            className={`nav-link d-inline-block px-3 ${
              currentPath === "/homeAdmin"
                ? "fw-bold text-white border-bottom border-white"
                : "text-white-50"
            }`}
          >
            Home
          </a>
          {/* <Link to="/EditarUsuario"
            className={`nav-link d-inline-block px-3 ${
              currentPath === "/"
                ? "fw-bold text-white border-bottom border-white"
                : "text-white-50"
            }`}
          >
            Usuarios
          </Link> */}
        </nav>
      </header>
    );
  }