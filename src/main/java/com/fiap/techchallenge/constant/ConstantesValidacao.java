package com.fiap.techchallenge.constant;

public class ConstantesValidacao {

    // CriarUsuarioDTO
    public static final String NOME_USUARIO_VAZIO = "Nome do usuário não pode ser vazio";
    public static final String EMAIL_INVALIDO = "O email deve ser válido";
    public static final String LOGIN_INVALIDO = "O login deve ter entre 3 e 50 caracteres";
    public static final String SENHA_VAZIA = "Senha não pode ser vazia";
    public static final String ROLE_NULA = "Role não pode ser nula";
    public static final String ENDERECO_NULO = "Endereço não pode ser nulo";

    // UsuarioDTO
    public static final String NOME_TAMANHO = "O nome deve ter entre 3 e 100 caracteres";
    public static final String LOGIN_TAMANHO = "O login deve ter entre 3 e 50 caracteres";

    // EnderecoDTO
    public static final String RUA_OBRIGATORIA = "Rua não pode ser vazia";
    public static final String NUMERO_OBRIGATORIO = "Número não pode ser vazio";
    public static final String COMPLEMENTO_OBRIGATORIO = "Complemento não pode ser vazio";
    public static final String BAIRRO_OBRIGATORIO = "Bairro não pode ser vazio";
    public static final String CIDADE_OBRIGATORIA = "Cidade não pode ser vazia";
    public static final String ESTADO_INVALIDO = "O estado deve ter exatamente 2 caracteres";
    public static final String CEP_INVALIDO = "O CEP deve ter exatamente 8 caracteres";

    // LoginUsuarioDTO
    public static final String LOGIN_OBRIGATORIO = "Login não pode ser vazio";
    public static final String SENHA_OBRIGATORIA = "Senha não pode ser vazia";

    // TrocaSenhaUsuarioDTO
    public static final String SENHA_ATUAL_OBRIGATORIA = "Senha atual não pode ser vazia";
    public static final String NOVA_SENHA_OBRIGATORIA = "Nova senha não pode ser vazia";

    private ConstantesValidacao() { }
}


