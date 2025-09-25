import { Link } from "react-router-dom";

export default function Sidebar() {
    return (
      <div className="d-flex flex-column flex-shrink-0 p-3 bg-dark text-white vh-100" style={{ width: "250px" }}>
        <div className="mb-4 text-center">
          <img
            src=""
            alt="logo"
            className="img-fluid mb-2"
          />
        </div>
  
        <ul className="nav nav-pills flex-column mb-auto">
          <li className="nav-item">
            <Link to="/homeAdmin" className="nav-link text-white">
              Home
            </Link>
          </li>
  
          <li>
            <a
              href="#submenucadastro"
              className="nav-link text-white"
              data-bs-toggle="collapse"
              aria-expanded="false"
            >
              Cadastro
            </a>
            <ul className="btn-toggle-nav list-unstyled collapse ps-3" id="submenucadastro">
              <li>
                
              <Link to="/usuarios" className="nav-link text-white-50">Usuários</Link>

              </li>
              <li>
                <Link to="/pets" className="nav-link text-white-50">Pets</Link>

              </li>
            </ul>
          </li>
        </ul>
      </div>
    );
  }