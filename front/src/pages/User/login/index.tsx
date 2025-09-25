import React, { useEffect, useState, type FormEvent } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

interface LoginRequest {
    email: string;
    senha: string;
}

interface LoginResponse{
    token: string;
}


export default function Login() {

          const navigator = useNavigate();

    const API_URL = "http://localhost:8080/"

    const [formData, setFormData] = useState<LoginRequest>({
        email: '',
        senha: '',
    });

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        
        setFormData(prevState => ({
            ...prevState,
            [name]: value,


        }));
    };

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        try {
            const response = await axios.post<LoginResponse>(API_URL + "auth/login",formData 
              
            );
            const token = response.data.token;
            localStorage.setItem("authToken",token)
            if(token !=null) {
                navigator("/homeAdmin")
            }






        // EXEMPLO DE FETCH
        //     const response = await fetch(API_URL + "auth/login", {
        //         method: 'POST',
        //         headers: {
        //             'Content-Type': 'application/json',
        //         },
        //      body: JSON.stringify(formData)
        //      });

        //        if(!response.ok){
        //         throw new Error("Erro ao logar o usuário!")
        //        }

        //        const data : LoginResponse = await response.json();
        //        console.log(data.token);


         } catch (error) {
        console.error;
         }

    }



return (
    <>

    <form onSubmit={handleSubmit}>
        <div className="mb-3">
            <label htmlFor="email" className="form-label text-light">Email</label>
            <input
                type="email"
                id="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
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
                name="senha"
                onChange={handleChange}
                value={formData.senha}
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
    </>
);


}
 