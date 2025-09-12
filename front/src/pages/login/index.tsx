import type { FormEvent } from "react";
import { useNavigate } from "react-router-dom";

export default function Login() {

    const navigate = useNavigate();

    const handleSubmit = (e: FormEvent) => {
        e.preventDefault();
        // lógica de login...
        navigate("/");
    };

    return (
        <form onSubmit={handleSubmit}>
            <div className="mb-3">
                <label htmlFor="email" className="form-label text-light">Email</label>
                <input
                    type="email"
                    id="email"
                    className="form-control bg-dark text-light border-secondary"
                    placeholder="seu@email.com"
                    required
                />
            </div>

            <div className="mb-3">
                <label htmlFor="password" className="form-label text-light">Senha</label>
                <input
                    type="password"
                    id="password"
                    className="form-control bg-dark text-light border-secondary"
                    placeholder="Digite sua senha"
                    required
                />
            </div>

            <button type="submit" className="btn btn-info w-100 fw-bold">
                Entrar
            </button>

            <div className="text-center mt-3">
                <a href="/register" className="small text-info text-decoration-none">
                    Criar conta
                </a>
            </div>
        </form>
    );

}