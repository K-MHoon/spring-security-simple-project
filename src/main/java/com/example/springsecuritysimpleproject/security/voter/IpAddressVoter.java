package com.example.springsecuritysimpleproject.security.voter;

import com.example.springsecuritysimpleproject.repository.security.AccessIpRepository;
import com.example.springsecuritysimpleproject.service.resource.SecurityResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class IpAddressVoter implements AccessDecisionVoter<Object> {

    private final SecurityResourceService securityResourceService;

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {

        WebAuthenticationDetails details = (WebAuthenticationDetails)authentication.getDetails();
        String remoteAddress = details.getRemoteAddress();

        List<String> accessIpList = securityResourceService.getAddressIpList();

        // 계속 심의해야 하므로 ACCESS_ABSTAIN
        for (String ipAddr : accessIpList) {
            if(remoteAddress.equals(ipAddr)) {
                return ACCESS_ABSTAIN;
            }
        }

        throw new AccessDeniedException("Invalid IpAddress");
    }
}
