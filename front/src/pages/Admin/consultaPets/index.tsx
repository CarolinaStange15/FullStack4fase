import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { buscarPetPorId, deletarPet, type Pet } from "../../../services/petService";

export default function DetalhesPet() {
  const navigate = useNavigate();
  const { id } = useParams();

  const [pet, setPet] = useState<Pet | null>(null);

  useEffect(() => {
    buscarPetPorId(id!)
      .then((res) => setPet(res))
      .catch((err) => console.error(err));
  }, [id]);

  if (!pet) {
    return <p className="text-center mt-4">Pet não encontrado</p>;
  }

  return (
    <>
      <div className="container mt-4">
        <div className="card shadow-sm">
          <div className="card-body">
            <h3 className="card-title text-dark">{pet.nome}</h3>
            <p>
              <strong>Idade aproximada:</strong> {pet.idadeAproximada}
            </p>
            <p>
              <strong>Descrição:</strong> {pet.descricao}
            </p>
            <p>
              <strong>Contato para adoção:</strong> {pet.contatoAdocao}
            </p>
            <p>
              <strong>Status:</strong> {pet.status}
            </p>
          </div>

          <div className="card-footer bg-transparent d-flex justify-content-end gap-2">
            <button
              className="btn btn-primary"
              onClick={() => navigate(`/pets/${pet.id}/editar`)}
            >
              Editar
            </button>

            <button
              className="btn btn-danger"
              onClick={async () => {
                try {
                  await deletarPet(pet.id);
                  alert("Pet deletado com sucesso!");
                  navigate("/homeAdmin");
                } catch (err) {
                  alert("Erro ao excluir pet!");
                  console.error(err);
                }
              }}
            >
              Excluir
            </button>
          </div>
        </div>
      </div>
    </>
  );
}
