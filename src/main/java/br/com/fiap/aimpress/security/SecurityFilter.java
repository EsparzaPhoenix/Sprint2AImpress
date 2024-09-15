package br.com.fiap.aimpress.security;

import br.com.fiap.aimpress.repository.user.UserRepository;
import br.com.fiap.aimpress.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Recuperar o token JWT da request
        String tokenJwt = request.getHeader("Authorization");
        if (tokenJwt != null){
            //Ajustar o token para tirar a palavra "Bearer"
            tokenJwt = tokenJwt.replace("Bearer ", "");

            //Utilizar o token service para validar o token e recuperar o login do usuario
            String login = tokenService.getSubject(tokenJwt);

            //Pesquisar o usuário pelo login
            UserDetails usuario =  userRepository.findByLogin(login);

            //Criar o objeto para setar o usuario no contexto
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            //Setar o usuario no contexto
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        //Chamar o filtro para dar continuidade
        filterChain.doFilter(request,response);
    }
}
