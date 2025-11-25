import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { buscarPetPorIdDetalhes, deletarPet, type PetDetalhes,  } from "../../../services/petService";
import { useSelector } from "react-redux";
import { solicitarAdocao } from "../../../services/adocaoService";
import type { RootState } from "../../../redux/store";

export default function DetalhesPetOng() {
  const navigate = useNavigate();
  const { id } = useParams();
  const token = useSelector((state: RootState) => state.auth.token);

 
  useEffect(() => {
    if (!token) {
      navigate("/"); 
    }
  }, [token, navigate]);


  const [pet, setPet] = useState<PetDetalhes | null>(null);

  useEffect(() => {
    buscarPetPorIdDetalhes(id!)
      .then(setPet)
      .catch(console.error);
  }, [id]);

  if (!pet) {
    return <p className="text-center mt-4">Pet não encontrado</p>;
  }

  return (
    <div className="container mt-4">
      <div className="card shadow-sm">
        <div className="card-body">
          <h3 className="card-title text-dark">{pet.nome}</h3>

          <p><strong>Idade aproximada:</strong> {pet.idadeAproximada}</p>

          <p><strong>Descrição:</strong> {pet.descricao}</p>

          <hr />

          <h5>Informações da ONG</h5>
          <p><strong>Nome:</strong> {pet.ong.nome}</p>
          <p><strong>E-mail:</strong> {pet.ong.email}</p>
          <p><strong>Telefone:</strong> {pet.ong.telefone}</p>
          <p><strong>Localização:</strong> {pet.ong.cidade} - {pet.ong.endereco}</p>
        </div>

        <div className="card-footer bg-transparent d-flex justify-content-end gap-2">
          <button
            className="btn btn-primary"
            onClick={() => navigate(`/home`)}
          >
            Voltar
          </button>
<button
  className="btn btn-success"
  onClick={async () => {
    if (!pet) return;

    const confirmar = window.confirm(
      "Ao solicitar a adoção, enviaremos seus dados para a ONG responsável pelo animal.\n\nDeseja continuar?"
    );

    if (!confirmar) return;

    try {
      await solicitarAdocao(pet.id);
      alert("Pedido de adoção enviado com sucesso! A ONG entrará em contato com você.");
      navigate("/home");
    } catch (err) {
      alert("Erro ao solicitar adoção!");
      console.error(err);
    }
  }}
>
  Solicitar adoção
</button>
        </div>
      </div>
    </div>
  );
}
