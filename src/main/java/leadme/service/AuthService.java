package leadme.service;

import bitcamp.java110.cms.domain.Member;

public interface AuthService {
    Member getMember(String email, String password, String memberType);
}
