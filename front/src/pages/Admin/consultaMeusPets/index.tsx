import Cards from "../../../components/body/cardsUserPet";
import { useEffect, useState } from "react";
import type { Pet } from "../../../services/petService";
import { buscarTodosPetsDisponiveis, buscarTodosPetsPorOng } from "../../../services/petService";
import { useSelector } from "react-redux";
import type { RootState } from "../../../redux/store";
import CardsAdminPet from "../../../components/body/cardsAdminPet";
import { useNavigate } from "react-router-dom";

export default function MeusPets() {
const [pets, setPets] = useState<Pet[]>([]);
const navigate = useNavigate();
const token = useSelector((state: RootState) => state.auth.token);

 
  useEffect(() => {
    if (!token) {
      navigate("/"); 
    }
  }, [token, navigate]);

  
 useEffect(() => {
  buscarTodosPetsPorOng()
    .then((res) => setPets(res))
    .catch((err) => console.error(err));
}, []);


  return (
    <main className="container my-5">
      <div className="text-center">
        <h1 className="display-4 fw-bold text-white bg-dark p-3 rounded">
          Meus pets
        </h1>
      </div>

      <section className="row mt-5 g-4">
        {pets.map((pet) => (
          <CardsAdminPet key={pet.id} id={pet.id} title={pet.nome} text={pet.descricao}   statusPet={pet.status}
 />
        ))}
      </section>
    </main>
  );
}
