import Cards from "../../../components/body/cards/cards";


import { useEffect, useState } from "react";
import axios from "axios";
import { Navigate, useNavigate } from "react-router-dom";

interface Pet {
  id: number;
  nome: string;
  descricao: string;
  contatoAdocao: string;
}


 export default function HomeAdmin(){
      const navigator = useNavigate();

   

  const [pets, setPets] = useState<Pet[]>([]);
  const API_URL = "http://localhost:8080/";

  useEffect(() => {
    const token = localStorage.getItem("authToken");
    
    if (!token) {
      navigator("/"); // redireciona se não tiver token
      return;
    }

    axios
      .get<Pet[]>(API_URL + "pets", {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((res) => setPets(res.data))
      .catch((err) => console.error(err));

      
  }, []);

  return (
    <main className="container my-5">
      <div className="text-center">
        <h1 className="display-4 fw-bold text-white bg-dark p-3 rounded">
          Pets Cadastrados
        </h1>
        <p className="lead text-muted mt-3">
          Lista de todos os pets cadastrados no sistema
        </p>
      </div>

      <section className="row mt-5 g-4">
        {pets.map((pet) => (
          <Cards
           key={pet.id}
            id={pet.id}         
            title={pet.nome}
            text={pet.descricao}
          />
        ))}
      </section>
    </main>
  );
}
