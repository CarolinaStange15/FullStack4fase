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

  const [pets, setPets] = useState<Pet[]>([]);
  const API_URL = "http://localhost:8080/";

  useEffect(() => {

    axios
      .get<Pet[]>(API_URL + "pets", {
        headers: {  },
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
