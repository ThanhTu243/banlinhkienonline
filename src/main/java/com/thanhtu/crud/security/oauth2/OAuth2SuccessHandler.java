package com.thanhtu.crud.security.oauth2;

import com.thanhtu.crud.entity.AccountsEntity;
import com.thanhtu.crud.entity.CustomerEntity;
import com.thanhtu.crud.repository.AccountsRepository;
import com.thanhtu.crud.repository.CustomerRepository;
import com.thanhtu.crud.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtProvider jwtProvider;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountsRepository accountsRepository;

    @Value("${hostname}")
    private String hostname;
    String hostname1="localhost:8080";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = (String) oAuth2User.getAttributes().get("email");
        String image=(String) oAuth2User.getAttributes().get("picture");
        String token = jwtProvider.createToken(email, "CUSTOMER");
        CustomerEntity customer=customerRepository.findCustomerEntitiesByGmailCustomerAndIsDelete(email,"NO");
        AccountsEntity account=accountsRepository.findAccountsEntitiesByGmail(email);;
        String customerId=customer.getCustomerId().toString();
        String Id=account.getAccountId().toString();
        String username=account.getUsername();
        String uri = UriComponentsBuilder.fromUriString("http://" + hostname + "/oauth2/redirect")
                .queryParam("token", token).queryParam("id",Id).queryParam("customerId",customerId).queryParam("image",image).queryParam("username",username).queryParam("userRoles","CUSTOMER")
                .build().toUriString();
        getRedirectStrategy().sendRedirect(request, response, uri);
    }
}
