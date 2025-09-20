import { useNavigate } from "react-router-dom";
import LayoutAdmin from "../../../components/layoutAdmin";
import { useState } from "react";


interface PetsRequest{
    nome: string;
    idadeAproximada: string;
    descricao: string;
    contatoAdocao: string;
    statusPet: StatusPet;
}   
const enum StatusPet {
    disponivel = 'disponivel',
    adotado = 'adotado'
}

export default function cadastrarPets(){
    const navigator = useNavigate();
    const API_URL = "http://localhost:8080/"

    const [formData, setFormData] = useState<PetsRequest>({
            nome: '',
            idadeAproximada: '',
            descricao: '',
            contatoAdocao: '',            
            statusPet: StatusPet.disponivel
        });
    

    return( 
        <LayoutAdmin></LayoutAdmin>





    );

}