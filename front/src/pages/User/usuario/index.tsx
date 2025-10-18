import { useEffect, useState } from "react";
import buscarTodosUsuarios, { type Usuario } from "../../../services/usuarioService";



 export default function Usuario() {

  const[usuarios,setUsuarios] = useState<Usuario[]>([]);

  useEffect(() =>{

    const carregarUsuarios = async ()=>{
      const usuarios = await buscarTodosUsuarios();
      setUsuarios(usuarios);



    }
    carregarUsuarios();

  },[]); // quando abrir essa tela ele vai executar essa bomba aqui



  return(
    <div className="mt-4">
      <h2 className="mt-4">Painel de usuários</h2>
      <table className="table table-striped table-hover">
        <thead className="thead-dark">
          <tr>
            <td>ID</td>
            <td>Nome</td>
            <td>CPF</td>
            <td>E-mail</td>
          </tr>
        </thead>
        <tbody>
          {
            usuarios.map((usuario)=> (
              <tr key={usuario.id}>
                <td>{usuario.id}</td>
                <td>{usuario.nome}</td>
                <td>{usuario.cpf}</td>
                <td>{usuario.email}</td>

              </tr>
            ))
          }
        </tbody>
      </table>

    </div>
  );
  
}
