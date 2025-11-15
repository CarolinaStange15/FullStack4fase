import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { cadastrarPet, StatusPet, buscarEspecies, type EspecieResponse, type PetsRequest } from "../../../services/petService";




export default function CadastrarPet() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState<PetsRequest>({
    nome: "",
    idadeAproximada: "",
    descricao: "",
    contatoAdocao: "",
    status: StatusPet.DISPONIVEL,
    especieId: 0,
    ongId:0
    
  });

   const [especies, setEspecies] = useState<EspecieResponse[]>([]);


  useEffect(() => {
    async function load() {
      const data = await buscarEspecies();
      setEspecies(data);
    }
    load();
  }, []);

  const handleChange = (
    event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = event.target;

    if (name === "especieId") {
      setFormData((prev) => ({
        ...prev,
        especieId: Number(value),
      }));
    } else {
      setFormData((prev) => ({
        ...prev,
        [name]: value,
      }));
    }
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    try {
      await cadastrarPet(formData);
      alert("Pet cadastrado com sucesso!");
      navigate("/meusPets");
    } catch (error) {
      alert("Erro ao cadastrar Pet!");
      console.error(error);

    };
  };


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
              name="especieId"
              id="especieId"
              value={formData.especieId}
              onChange={handleChange}
              className="form-select"
              required
            >
              <option value={0} disabled>
                Selecione uma espécie
              </option>
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
