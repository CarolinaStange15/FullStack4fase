package com.senac.AulaFullStack.domain.interfaces;

public interface IEnvioEmail {

    void enviarEmailSimples(String para, String assunto, String texto);

    void enviarEmailComTemplate(String para, String assunto, String texto);

    void enviarEmailSolicitacaoCadastroOng(String para, String assunto, String texto);

    void enviarEmailAprovacaoCadastroOng(String para, String assunto, String texto);

    void enviarEmailRecusaCadastroOng(String para, String assunto, String texto);

    void enviarEmailSolicitacaoAdocaoAnimal(String para, String assunto, String texto);

    void enviarEmailAprovacaoAdocaoAnimal(String para, String assunto, String texto);

    void enviarEmailRecusaAdocaoAnimal(String para, String assunto, String texto);



}
