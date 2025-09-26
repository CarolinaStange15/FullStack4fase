import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

interface Especie {
  id: number;
  nome: string;
}

interface Pet {
  id: number;
  nome: string;
  idadeAproximada: string;
  descricao: string;
  contatoAdocao: string;
  status: "DISPONIVEL" | "ADOTADO";
  especie: {
    id: number;
  };
}

export default function EditarPet() {
  const { id } = useParams();
  const navigate = useNavigate();
  const API_URL = "http://localhost:8080/";

  const [pet, setPet] = useState<Pet | null>(null);
  const [especies, setEspecies] = useState<Especie[]>([]);

  // Buscar pet pelo ID
  useEffect(() => {
    const token = localStorage.getItem("authToken");
    if (!token) return;

    axios
      .get<Pet>(`${API_URL}pets/${id}`, {
        headers: { },
      })
      .then((res) => setPet(res.data))
      .catch((err) => console.error(err));
  }, [id]);

  // Buscar espécies
  useEffect(() => {
   

    axios
      .get<Especie[]>(`${API_URL}especies`, {
        headers: { },
      })
      .then((res) => setEspecies(res.data))
      .catch((err) => console.error(err));
  }, []);

  if (!pet) return <p className="text-center mt-4">Carregando...</p>;

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;

    if (name === "especie") {
      setPet({
        ...pet,
        especie: {
          id: Number(value),
        },
      });
    } else {
      setPet({
        ...pet,
        [name]: value,
      });
    }
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();


    const petRequestDto = {
    nome: pet.nome,
    idadeAproximada: pet.idadeAproximada,
    descricao: pet.descricao,
    contatoAdocao: pet.contatoAdocao,
    especieId: pet.especie.id, 
    status: pet.status,
  };

    try {
      await axios.put(`${API_URL}pets/${id}`, petRequestDto, {
        headers: {
          "Content-Type": "application/json",
        },
      });

      navigate(`/pets/${id}`); // ou outro destino desejado
    } catch (err) {
      console.error("Erro ao editar pet:", err);
    }
  };

  const toggleStatus = () => {
    if (!pet) return;
    setPet({
      ...pet,
      status: pet.status === "DISPONIVEL" ? "ADOTADO" : "DISPONIVEL",
    });
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4 text-dark">Editar Pet</h2>

      <form onSubmit={handleSubmit} className="p-4 border rounded bg-light">
        <div className="mb-3">
          <label className="form-label text-dark">Nome do animal</label>
          <input
            type="text"
            name="nome"
            value={pet.nome}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label text-dark">Idade aproximada</label>
          <input
            type="text"
            name="idadeAproximada"
            value={pet.idadeAproximada}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label text-dark">Descrição</label>
          <input
            type="text"
            name="descricao"
            value={pet.descricao}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label text-dark">Contato para adoção</label>
          <input
            type="text"
            name="contatoAdocao"
            value={pet.contatoAdocao}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label text-dark">Espécie</label>
          <select
            name="especie"
            value={pet.especie.id}
            onChange={handleChange}
            className="form-select"
            required
          >
            <option value={0} disabled>Selecione uma espécie</option>
            {especies.map((e) => (
              <option key={e.id} value={e.id}>
                {e.nome}
              </option>
            ))}
          </select>
        </div>

        <div className="mb-3 d-flex justify-content-between align-items-center">
          <span>
            <strong>Status:</strong> {pet.status}
          </span>
          <button
            type="button"
            className="btn btn-dark"
            onClick={toggleStatus}
          >
            {pet.status === "DISPONIVEL"
              ? "Marcar como Adotado"
              : "Marcar como Disponível"}
          </button>
        </div>

        <div className="d-flex gap-2 mt-3">
          <button
            type="submit"
            className="btn btn-dark"
          >
            Salvar informações
          </button>
          </div>
      </form>
    </div>
  );
}
