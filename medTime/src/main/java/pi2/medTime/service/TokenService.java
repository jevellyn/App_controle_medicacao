package pi2.medTime.service;

import com.auth0.jwt.JWT;  // Importa a biblioteca JWT da Auth0 para criação e verificação de tokens JWT.
import com.auth0.jwt.algorithms.Algorithm;  // Importa o algoritmo HMAC256 para assinar o token.
import com.auth0.jwt.exceptions.JWTCreationException;  // Exceção lançada durante a criação do token.
import com.auth0.jwt.exceptions.JWTVerificationException;  // Exceção lançada durante a verificação do token.
import org.springframework.beans.factory.annotation.Value;  // Importa a anotação Value para ler configurações do arquivo properties.
import org.springframework.stereotype.Service;  // Anotação para definir o serviço.
import pi2.medTime.model.Usuario;  // Importa a classe Usuario, que será usada para gerar o token.

import java.time.Instant;  // Importa Instant para trabalhar com data e hora no formato UTC.
import java.time.LocalDateTime;  // Importa LocalDateTime para gerar data e hora locais.
import java.time.ZoneOffset;  // Importa ZoneOffset para definir o fuso horário.

@Service
public class TokenService {

    @Value("${api.security.token.secret}")  // Obtém a chave secreta do arquivo de configuração (por exemplo, application.properties).
    private String secret;

    /**
     * Gera um token JWT para o usuário.
     *
     * @param user O usuário para o qual o token será gerado.
     * @return O token JWT gerado.
     */
    public String generateToken(Usuario user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Cria o token JWT com as informações do usuário.
            String token = JWT.create()
                    .withIssuer("medTime")  // Define o emissor do token.
                    .withSubject(user.getEmail())  // Define o assunto (usuário) como o e-mail do usuário.
                    .withExpiresAt(generateExpirationDate())  // Define a data de expiração do token.
                    .sign(algorithm);  // Assina o token com o algoritmo HMAC256.

            return token;  // Retorna o token gerado.

        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro durante a geração do token", exception);
        }
    }

    /**
     * Gera a data de expiração para o token (2 horas após a geração).
     *
     * @return A data de expiração como um objeto Instant.
     */
    private Instant generateExpirationDate(){
        // Gera a data de expiração (2 horas após a data e hora atual).
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));  // Considerando o fuso horário -03:00 (Brasília).
    }

    /**
     * Valida e verifica o token JWT.
     *
     * @param token O token JWT a ser validado.
     * @return O assunto (usuário) do token, que é o e-mail do usuário, ou uma string vazia se o token for inválido.
     */
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Verifica se o token é válido e retorna o assunto (usuário).
            return JWT.require(algorithm)  // Cria o validador com o algoritmo HMAC256.
                    .withIssuer("medTime")  // Verifica se o emissor é "medTime".
                    .build()  // Constrói o validador.
                    .verify(token)  // Verifica o token.
                    .getSubject();  // Retorna o assunto (usuário), que é o e-mail.

        }catch (JWTVerificationException exception) {
            // Retorna uma string vazia se o token for inválido.
            return "";
        }
    }
}
