import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";

interface PetsRequest {
  nome: string;
  idadeAproximada: string;
  descricao: string;
  contatoAdocao: string;
  status: StatusPet;
  especie: {
    id: number;
  };
}
const enum StatusPet {
  DISPONIVEL = "DISPONIVEL",
  ADOTADO = "ADOTADO",
}

interface PetsResponse {
  nome: string;
  descricao: string;
}

interface EspecieResponse {
  id: number;
  nome: string;
}

export default function E() {
  const navigator = useNavigate();
  const API_URL = "http://localhost:8080/";

  const [formData, setFormData] = useState<PetsRequest>({
    nome: "",
    idadeAproximada: "",
    descricao: "",
    contatoAdocao: "",
    status: StatusPet.DISPONIVEL,
    especie: {
      id: 0,
    },
  });

  const handleChange = (
    event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = event.target;

    if (name === "especie") {
      setFormData((prevState) => ({
        ...prevState,
        especie: {
          id: Number(value),
        },
      }));
    } else {
      setFormData((prevState) => ({
        ...prevState,
        [name]: value,
      }));
    }
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    
      console.log("Payload enviado:", formData);

      try{
      const response = await axios.post<PetsResponse>(
        API_URL + "pets",
        formData,
        {
          headers: {
            "Content-Type": "application/json",

          },
        }
      );

      const pet = response.data;
      console.log("Pet cadastrado:", pet);
            alert(`O pet ${pet.nome} foi cadastrado com sucesso!`)

      navigator("/homeAdmin");
    }catch(error){
      alert("Erro ao cadastrar PET!")
    }
    }

  const [especies, setEspecies] = useState<EspecieResponse[]>([]);

  useEffect(() => {
    const token = localStorage.getItem("authToken");

    axios
      .get<EspecieResponse[]>(API_URL + "especies", {
        headers: {
         
        },
      })
      .then((res) => setEspecies(res.data))
      .catch((err) => console.error(err));
  }, []);


  return (
    <div className="container mt-5">
      <h2 className="mb-4 text-dark">Cadastrar Pet</h2>

      <form onSubmit={handleSubmit} className="p-4 border rounded bg-light">
        <div className="mb-3">
          <label className="form-label text-dark">Nome do animal</label>
          <input
            type="text"
            id="nomePet"
            name="nome"
            value={formData.nome}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label text-dark">Idade aproximada</label>
          <input
            type="text"
            id="idadePet"
            name="idadeAproximada"
            value={formData.idadeAproximada}
            onChange={handleChange}
            className="form-control"
            placeholder="Exemplo: 7 meses"
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label text-dark">Descrição</label>
          <input
            type="text"
            id="descricaoPet"
            name="descricao"
            value={formData.descricao}
            onChange={handleChange}
            className="form-control"
            placeholder="Nos fale mais sobre o animalzinho"
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label text-dark">Contato</label>
          <input
            type="text"
            id="contatoAdocao"
            name="contatoAdocao"
            value={formData.contatoAdocao}
            onChange={handleChange}
            className="form-control"
            placeholder="Email ou telefone"
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label text-dark">Espécie</label>
          <select
            name="especie"
            id="especieId"
            value={formData.especie.id}
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

        <button type="submit" className="btn btn-primary w-100">
          Cadastrar Pet
        </button>
      </form>
    </div>
  );
}
