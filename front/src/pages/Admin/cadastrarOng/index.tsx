import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { criarOng, type OngRequestDto,  } from "../../../services/ongService";
import { vincularOng } from "../../../services/usuarioService";
import { useSelector } from "react-redux";
import type { RootState } from "../../../redux/store";

export default function CadastrarOng() {
  const navigate = useNavigate();
  const token = useSelector((state: RootState) => state.auth.token);

 
  useEffect(() => {
    if (!token) {
      navigate("/"); 
    }
  }, [token, navigate]);

  const [formData, setFormData] = useState<OngRequestDto>({
    id: 0,
    nome: "",
    descricao: "",
    email: "",
    cnpj: "",
    telefone: "",
    endereco: "",
    cidade: "",
  });

  const handleChange = (
    event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    const { name, value } = event.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    try {
      const ong = await criarOng(formData);
      await vincularOng(ong.id);

      alert("ONG criada e vinculada com sucesso!");
      navigate("/home");
    } catch (error) {
      console.error(error);
      alert("Erro ao criar ou vincular ONG");
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4 text-dark">Cadastrar ONG</h2>

      <form onSubmit={handleSubmit} className="p-4 border rounded bg-light">
        <div className="mb-3">
          <label className="form-label text-dark">Nome da ONG</label>
          <input
            type="text"
            name="nome"
            value={formData.nome}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label text-dark">Descrição</label>
          <textarea
            name="descricao"
            value={formData.descricao}
            onChange={handleChange}
            className="form-control"
            placeholder="Nos fale sobre sua ONG"
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label text-dark">E-mail</label>
          <input
            type="text"
            name="email"
            value={formData.email}
            onChange={handleChange}
            className="form-control"
            required
          />

        </div>
        <div className="mb-3">
          <label className="form-label text-dark">CNPJ</label>
          <input
            type="text"
            name="cnpj"
            value={formData.cnpj}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label text-dark">Telefone</label>
          <input
            type="text"
            name="telefone"
            value={formData.telefone}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label text-dark">Cidade</label>
          <input
            type="text"
            name="cidade"
            value={formData.cidade}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label text-dark">Endereço</label>
          <input
            type="text"
            name="endereco"
            value={formData.endereco}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>

        <button type="submit" className="btn btn-primary w-100">
          Cadastrar ONG
        </button>
      </form>
    </div>
  );
}
