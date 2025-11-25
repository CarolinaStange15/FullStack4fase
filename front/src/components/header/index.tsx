import { Link, useLocation } from "react-router-dom";

export default function Header() {
    const currentPath = window.location.pathname;
      const { pathname } = useLocation();

  
    return (
      <header className="bg-dark p-3">
        <nav className="navbar navbar-expand-lg navbar-dark container">
        <Link
          to="/home"
          className={`nav-link d-inline-block px-3 ${
            pathname === "/home"
              ? "fw-bold text-white border-bottom border-white"
              : "text-white-50"
          }`}
        >
          Home
        </Link>
          { <Link to="/usuario/editar"
            className={`nav-link d-inline-block px-3 ${
              currentPath === "/"
                ? "fw-bold text-white border-bottom border-white"
                : "text-white-50"
            }`}
          >
            Meu usuario
          </Link> }
<Link
  to="/adocoes/meus-pedidos"
  className={`nav-link d-inline-block px-3 ${
    currentPath === "/adocoes/meus-pedidos"
      ? "fw-bold text-white border-bottom border-white"
      : "text-white-50"
  }`}
>
  Minhas solicitações
</Link>


        </nav>
      </header>
    );
  }