import Cards from "../../../components/body/cardsUserPet";
import { useEffect, useState } from "react";
import type { Pet } from "../../../services/petService";
import { buscarTodosPetsDisponiveis } from "../../../services/petService";
import { useSelector } from "react-redux";
import type { RootState } from "../../../redux/store";
import { Link } from "react-router-dom";
import api from "../../../services/api";

interface UsuarioLogado {
  id: number;
  nome: string;
  email: string;
  cpf: string;
  ongId?: number | null;
}

export default function Home() {
  const [pets, setPets] = useState<Pet[]>([]);
  const [usuario, setUsuario] = useState<UsuarioLogado | null>(null);

  useEffect(() => {
    // Buscar pets
    buscarTodosPetsDisponiveis()
      .then((res) => setPets(res))
      .catch((err) => console.error(err));

    // Buscar usuário logado
    const fetchUser = async () => {
      try {
        const response = await api.get("/usuarios/me");
        setUsuario(response.data);
      } catch (err) {
        console.error("Erro ao buscar usuário", err);
      }
    };

    fetchUser();
  }, []);

  return (
    <main className="container my-5">
      <div className="text-center">

        {/* === MOSTRAR O LINK SOMENTE SE NÃO POSSUIR ONG === */}
        {!usuario?.ongId && (
          <Link
            to="/ong/cadastrar"
            className="d-inline-block mb-3 p-2 px-3 text-decoration-none text-white bg-primary rounded shadow-sm"
            style={{ fontWeight: "500" }}
          >
            Você possui uma ONG? Cadastre sua ONG clicando aqui!
          </Link>
        )}

        <h1 className="display-4 fw-bold text-white bg-dark p-3 rounded">
          Pets Disponíveis para adoção
        </h1>
      </div>

      <section className="row mt-5 g-4">
        {pets.map((pet) => (
          <Cards 
            key={pet.id} 
            id={pet.id} 
            title={pet.nome} 
            text={pet.descricao}  
            especie={pet.especieid?.nome ?? "Não informado"}  

          />
        ))}
      </section>
    </main>
  );
}
