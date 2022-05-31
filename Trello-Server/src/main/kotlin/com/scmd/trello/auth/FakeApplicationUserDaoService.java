package com.scmd.trello.auth;

import com.google.common.collect.Lists;
import com.scmd.trello.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.scmd.trello.security.ApplicationUserRole.ADMIN;
import static com.scmd.trello.security.ApplicationUserRole.STUDENT;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDAO {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers(){
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
          new ApplicationUser(
                  "test1",
                  passwordEncoder.encode("test1"),
                  ADMIN.getGrantedAuthorities(),
                  true,
                  true,
                  true,
                  true
          ),
                new ApplicationUser(
                        "test2",
                        passwordEncoder.encode("test2"),
                        STUDENT.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );
        return applicationUsers;
    }
}
