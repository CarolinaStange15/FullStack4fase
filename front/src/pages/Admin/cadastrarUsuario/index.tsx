import { useNavigate } from "react-router-dom";
import { useState } from "react";
import axios from "axios";

interface UsuarioRequest {
    nome: string;
    cpf: string;
    email: string;
    senha: string;
    role: string;
}

interface UsuarioResponse {
    id: number;
    nome: string;
    email: string;
    role: string;
}

export default function CadastrarUsuario() {
    const navigate = useNavigate();
    const API_URL = "http://localhost:8080/";

    const [formData, setFormData] = useState<UsuarioRequest>({
        nome: "",
        cpf: "",
        email: "",
        senha: "",
        role: "USER", // valor padrão
    });

    const handleChange = (
        event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
    ) => {
        const { name, value } = event.target;
        setFormData((prevState) => ({
            ...prevState,
            [name]: value,
        }));
    };

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        try {
            const token = localStorage.getItem("authToken");
            if (!token) {
                navigate("/");
                return;
            }

            const response = await axios.post<UsuarioResponse>(
                API_URL + "usuarios", // ajusta conforme teu endpoint
                formData,
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        "Content-Type": "application/json",
                    },
                }
            );


            navigate("/homeAdmin");
        } catch (error) {
            console.error("Erro ao cadastrar usuário:", error);
        }
    };

    return (
        <div className="container mt-5">
            <h2 className="mb-4 text-dark">Cadastrar Usuário</h2>

            <form onSubmit={handleSubmit} className="p-4 border rounded bg-light">
                <div className="mb-3">
                    <label className="form-label text-dark">Nome</label>
                    <input
                        type="text"
                        id="nome"
                        name="nome"
                        value={formData.nome}
                        onChange={handleChange}
                        className="form-control"
                        required
                    />
                </div>

                <div className="mb-3">
                    <label className="form-label text-dark">CPF</label>
                    <input
                        type="text"
                        id="cpf"
                        name="cpf"
                        value={formData.cpf}
                        onChange={handleChange}
                        className="form-control"
                        placeholder="000.000.000-00"
                        required
                    />
                </div>

                <div className="mb-3">
                    <label className="form-label text-dark">Email</label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        className="form-control"
                        required
                    />
                </div>

                <div className="mb-3">
                    <label className="form-label text-dark">Senha</label>
                    <input
                        type="password"
                        id="senha"
                        name="senha"
                        value={formData.senha}
                        onChange={handleChange}
                        className="form-control"
                        required
                    />
                </div>

                <div className="mb-3">
                    <label className="form-label text-dark">Papel</label>
                    <select
                        id="role"
                        name="role"
                        value={formData.role}
                        onChange={handleChange}
                        className="form-select"
                        required
                    >
                        <option value="USER">Usuário</option>
                        <option value="ADMIN">Administrador</option>
                    </select>
                </div>

                <button type="submit" className="btn btn-primary w-100">
                    Cadastrar Usuário
                </button>
            </form>
        </div>
    );
}
