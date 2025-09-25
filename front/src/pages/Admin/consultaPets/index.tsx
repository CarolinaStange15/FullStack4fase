import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

interface Pet {
  id: number;
  nome: string;
  idadeAproximada: string;
  descricao: string;
  contatoAdocao: string;
  status: string;
  img: string;
}

export default function DetalhesPet() {
    const API_URL = "http://localhost:8080/";

  const { id } = useParams();
  const [pet, setPet] = useState<Pet | null>(null);
  const navigate = useNavigate(); 

  useEffect(() => {
     const token = localStorage.getItem("authToken");
    if (!token) return;

     axios
    .get<Pet>(`http://localhost:8080/pets/${id}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    .then((res) => setPet(res.data))
    .catch((err) => console.error(err));
}, [id]);

  if (!pet) {
    return <p className="text-center mt-4">Carregando...</p>;
  }

  return (
    <>
    <div className="container mt-4">
      <div className="card">
        <img src={pet.img} className="card-img-top" alt={pet.nome} />
        <div className="card-body">
          <h3 className="card-title">{pet.nome}</h3>
          <p><strong>Idade aproximada:</strong> {pet.idadeAproximada}</p>
          <p><strong>Descrição:</strong> {pet.descricao}</p>
          <p><strong>Contato para adoção:</strong> {pet.contatoAdocao}</p>
          <p><strong>Status:</strong> {pet.status}</p>
        </div>
      </div>
    </div>
          <div className="d-flex gap-2 mt-3">
  <button
    className="btn btn-dark"
    onClick={() => navigate(`/pets/${pet.id}/editar`)}
  >
    Editar
  </button>

  <button
    className="btn btn-danger"
    onClick={async () => {
  const token = localStorage.getItem("authToken");
  console.log("TOKEN:", token); // <-- teste aqui
  if (!token) return;

  try {
    await axios.delete(`${API_URL}pets/${pet.id}`, {
      headers: { Authorization: `Bearer ${token}` },
    });
    navigate("/pets");
  } catch (err) {
    console.error("Erro ao excluir pet:", err);
  }
    }}
  >
    Excluir
  </button>
</div>

          
          </>
  );
}