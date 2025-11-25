import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import {
  buscarPetPorId,
  buscarEspecies,
  editarPet,
  type PetsRequest,
  type EspecieResponse,
  StatusPet
} from "../../../services/petService";

export default function EditarPet() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [formData, setFormData] = useState<PetsRequest>({
    id: 0 ,    
    nome: "",
    idadeAproximada: "",
    descricao: "",
    status: StatusPet.DISPONIVEL,
    especieId: 0  });

  const [especies, setEspecies] = useState<EspecieResponse[]>([]);
  const [loading, setLoading] = useState(true);

  // Buscar pet
  useEffect(() => {
    async function carregarPet(): Promise<void> {
      if (!id) return;

      const data = await buscarPetPorId(id);
      setFormData({
  id: data.id,
  nome: data.nome,
  idadeAproximada: data.idadeAproximada,
  descricao: data.descricao,
  status: data.status ?? StatusPet.DISPONIVEL,
  especieId: data.especieid?.id ?? 0
});


      setLoading(false);
    }

    carregarPet();
  }, [id]);

  // Buscar espécies
  useEffect(() => {
    async function carregarEspecie() {
      const data = await buscarEspecies();
      setEspecies(data);
    }
    carregarEspecie();
  }, []);

  if (loading) return <p>Carregando...</p>;

  // Handle Change
  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;

    setFormData((prev) => ({
      ...prev,
[name]:
      name === "especieId"
        ? Number(value)
        : name === "status"
        ? (value as StatusPet)
        : value,      
    }));
  };

  // Submit
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      await editarPet(id!, formData);
      alert("Pet atualizado com sucesso!");
      navigate(`/pets/${id}`);
    } catch (err) {
      console.error(err);
      alert("Erro ao atualizar o pet");
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4 text-dark">Editar Pet</h2>

      <form onSubmit={handleSubmit} className="p-4 border rounded bg-light">
        <div className="mb-3">
          <label className="form-label">Nome</label>
          <input type="text" name="nome" value={formData.nome} onChange={handleChange} className="form-control" required />
        </div>

        <div className="mb-3">
          <label className="form-label">Idade aproximada</label>
          <input type="text" name="idadeAproximada" value={formData.idadeAproximada} onChange={handleChange} className="form-control" required />
        </div>

        <div className="mb-3">
          <label className="form-label">Descrição</label>
          <input type="text" name="descricao" value={formData.descricao} onChange={handleChange} className="form-control" required />
        </div>


        <div className="mb-3">
          <label className="form-label">Espécie</label>
          <select name="especieId" value={formData.especieId} onChange={handleChange} className="form-select">
            <option value={0} disabled>Selecione</option>
            {especies.map((e) => (
              <option key={e.id} value={e.id}>{e.nome}</option>
            ))}
          </select>
        </div>
        <button
  type="button"
  onClick={() =>
    setFormData(prev => ({
      ...prev,
      status: prev.status === StatusPet.DISPONIVEL
        ? StatusPet.ADOTADO
        : StatusPet.DISPONIVEL
    }))
  }
  className={`btn w-100 mb-3 ${
    formData.status === StatusPet.DISPONIVEL ? "btn-success" : "btn-secondary"
  }`}
>
  {formData.status === StatusPet.DISPONIVEL ? "Marcar como Adotado" : "Marcar como Disponível"}
</button>
 

        <button type="submit" className="btn btn-primary w-100">Salvar alterações</button>
      </form>
    </div>
  );
}
