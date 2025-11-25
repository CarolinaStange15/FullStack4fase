import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import api from "../../services/api"; 
interface UsuarioLogado {
  id: number;
  nome: string;
  email: string;
  cpf: string;
  ongId?: number | null; 
}

export default function Sidebar() {

  const navigate = useNavigate();
  const [usuario, setUsuario] = useState<UsuarioLogado | null>(null);

  useEffect(() => {
    const buscarUsuario = async () => {
      try {
        const resposta = await api.get("/usuarios/me"); 
        setUsuario(resposta.data);
      } catch (error) {
        console.error("Erro ao buscar usuário logado:", error);
      }
    };

    buscarUsuario();
  }, []);

  return (
    <div className="d-flex flex-column flex-shrink-0 p-3 bg-dark text-white vh-100" style={{ width: "250px" }}>
      <div className="mb-4 text-center">
        <img
          src="/img/logo.png"
          alt="logo"
          className="img-fluid mb-2"
          width={"100px"}
          onClick={() => navigate("/")}
        />
      </div>

      <ul className="nav nav-pills flex-column mb-auto">

        {/* <li className="nav-item">
          <Link to="/home" className="nav-link text-white">Home</Link>
        </li> */}

        {usuario?.ongId && (
          <>
            <li>
              <a
                href="#submenuPets"
                className="nav-link text-white"
                data-bs-toggle="collapse"
                aria-expanded="false"
              >
                Pets
              </a>
              <ul className="btn-toggle-nav list-unstyled collapse ps-3" id="submenuPets">
                <li>
                  <Link to="/pets/cadastrar" className="nav-link text-white-50">Cadastrar Pet</Link>
                </li>
                <li>
                  <Link to="/pets/consultar" className="nav-link text-white-50">Meus Pets</Link>
                </li>
              </ul>
            </li>

            <li>
              <a
                href="#submenuAdocoes"
                className="nav-link text-white"
                data-bs-toggle="collapse"
                aria-expanded="false"
              >
                Adoções
              </a>
              <ul className="btn-toggle-nav list-unstyled collapse ps-3" id="submenuAdocoes">
                <li>
                  <Link to="/adocoes/pendentes" className="nav-link text-white-50">Pedidos pendentes</Link>
                </li>
                <li>
                  <Link to="/adocoes/aprovadas" className="nav-link text-white-50">Pedidos aprovados</Link>
                </li>
                <li>
                  <Link to="/adocoes/reprovadas" className="nav-link text-white-50">Pedidos reprovados</Link>
                </li>
              </ul>
            </li>
                   <li>
                    <Link to="/ongs/minhaOng" className="nav-link text-white-50">Minha Ong</Link>
                  </li>

          </>
        )}

        {/* <li className="nav-item">
          <Link to="/usuariosConsultar" className="nav-link text-white">
            Lista Usuários
          </Link>
        </li> */}

      </ul>
    </div>
  );
}
