import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import {buscarUsuarioLogado} from "../../../services/usuarioService";

import {atualizarMinhaOng,  buscarOngPorId,  type OngRequestDto} from "../../../services/ongService";

export default function EditarOng() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState<OngRequestDto>({
    id: 0,
    nome: "",
    email: "",
    descricao: "",
    cnpj: "",
    telefone: "",
    endereco: "",
    cidade: "",
  });

  // Carrega dados da ONG do usuário
  useEffect(() => {
    async function load() {
      try {
        const usuario = await buscarUsuarioLogado();

        if (!usuario.ongId) {
          alert("Você não possui uma ONG vinculada!");
          return;
        }

        const ong = await buscarOngPorId(usuario.ongId);

        setFormData({
          id: ong.id,
          nome: ong.nome,
          email: ong.email,
          descricao: ong.descricao,
          cnpj: ong.cnpj,
          telefone: ong.telefone,
          endereco: ong.endereco,
          cidade: ong.cidade,
        });
      } catch (error) {
        console.error(error);
        alert("Erro ao carregar dados da ONG!");
      }
    }

    load();
  }, []);

  const handleChange = (
    event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
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
      await atualizarMinhaOng(formData);
      alert("ONG atualizada com sucesso!");
      navigate("/home");
    } catch (error) {
      alert("Erro ao atualizar ONG!");
      console.error(error);
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4 text-dark">Editar ONG</h2>

      <form onSubmit={handleSubmit} className="p-4 border rounded bg-light">

        
        <div className="mb-3">
          <label className="form-label text-dark">Nome</label>
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
          <label className="form-label text-dark">Email</label>
          <input
            type="email"
            name="email"
            value={formData.email}
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
            value={formData.descricao}
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

        {/* Endereço */}
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

        {/* Cidade */}
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

        <button type="submit" className="btn btn-primary w-100">
          Salvar alterações
        </button>
      </form>
    </div>
  );
}
