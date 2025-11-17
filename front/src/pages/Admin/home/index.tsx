import Cards from "../../../components/body/cards/cards";
import { useEffect, useState } from "react";
import type { Pet } from "../../../services/petService";
import { buscarTodosPetsDisponiveis } from "../../../services/petService";
import { useSelector } from "react-redux";
import type { RootState } from "../../../redux/store";

export default function HomeAdmin() {
  const [pets, setPets] = useState<Pet[]>([]);
  const token = useSelector((state: RootState) => state.auth.token);

  useEffect(() => {
    if (!token) return;

    buscarTodosPetsDisponiveis()
      .then((res) => setPets(res))
      .catch((err) => console.error(err));
  }, [token]);

  return (
    <main className="container my-5">
      <div className="text-center">
        <h1 className="display-4 fw-bold text-white bg-dark p-3 rounded">
          Pets Cadastrados
        </h1>
      </div>

      <section className="row mt-5 g-4">
        {pets.map((pet) => (
          <Cards key={pet.id} id={pet.id} title={pet.nome} text={pet.descricao} />
        ))}
      </section>
    </main>
  );
}
