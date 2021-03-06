package com.infoshareacademy.pomaranczowi.financialanalyser.services;

import com.infoshareacademy.pomaranczowi.financialanalyser.domain.User;
import com.infoshareacademy.pomaranczowi.financialanalyser.repository.UserRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletConfig;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

@Stateless
public class UserServiceImpl implements UserService {

    @EJB
    UserRepository userRepository;

    public User getUserInfo(ServletConfig config, String accessToken) {

        String url = "https://"+config.getServletContext().getInitParameter("com.auth0.domain")+"/userinfo";

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(url);
        Response response = webTarget.request().header("Authorization","Bearer "+accessToken).get();

        User user = response.readEntity(User.class);
        if (user != null) userRepository.addUser(user);

        return user;
    }
}
