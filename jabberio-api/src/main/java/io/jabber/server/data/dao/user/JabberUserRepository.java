package io.jabber.server.data.dao.user;

import io.jabber.server.data.models.user.JabberUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface JabberUserRepository
    extends JpaRepository<JabberUser, Long>, JpaSpecificationExecutor<JabberUser> {

}
