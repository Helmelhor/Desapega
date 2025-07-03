package com.desapega.Desapega_System.repository;

import java.util.HashMap;
import java.util.Map;

public class UsuarioRepository {
    private static final Map<String, String> usuarios = new HashMap<>();

    static {
        usuarios.put("admin", "1234");
    }

    public static boolean autenticar(String login, String senha) {
        String senhaCadastrada = usuarios.get(login);
        return senhaCadastrada != null && senhaCadastrada.equals(senha);
    }
}
