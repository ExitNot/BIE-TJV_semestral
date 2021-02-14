package cz.cvut.fit.tjv.semestral.business;

import cz.cvut.fit.tjv.semestral.data.AuthRepository;
import cz.cvut.fit.tjv.semestral.data.entities.Auth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

@SpringBootTest(classes = {cz.cvut.fit.tjv.semestral.business.AuthServiceImpl.class})
class AuthServiceImplTest {
    @Autowired
    private AuthServiceImpl authService;
    @MockBean
    private AuthRepository authRepository;

    private final Auth auth1 = new Auth( "mynickname", null, "3wRv!re6");
    private final Auth auth2 = new Auth( "mynickname142", null, "H5v$!rv6");

    @Test
    void create() {
        Example<Auth> auth_ex = Example.of(auth1);

        BDDMockito.given(authRepository.findAll(auth_ex)).willReturn(Collections.emptyList());
        BDDMockito.given(authRepository.save(auth1)).willReturn(auth1);

        Auth result = authService.create(auth1);
        Assertions.assertEquals(auth1, result);
    }

    @Test
    void readById() {
        Optional<Auth> optional_auth1 = Optional.of(auth1);

        BDDMockito.given(authRepository.findById(auth1.getLogin())).willReturn(optional_auth1);

        Optional<Auth> result = authService.readById(auth1.getLogin());
        Assertions.assertEquals(optional_auth1.get(), result.get());
    }

    @Test
    void readAll() {
        final List<Auth> data = List.of(auth1, auth2);
        final Pageable pageable = PageRequest.of( 0, 2);
        final Page<Auth> pageExpected = new PageImpl<>(data, pageable, 3);

        BDDMockito.given(authRepository.findAll(pageable)).willReturn(pageExpected);

        Assertions.assertEquals(pageExpected ,authService.readAll(pageable));
    }

    @Test
    void update() {
        Auth old_auth = auth1;
        old_auth.setPasswordHash("---------");

        doNothing().when(authRepository).deleteById(old_auth.getLogin());
        BDDMockito.given(authRepository.save(auth2)).willReturn(auth2);
        BDDMockito.given(authRepository.findById(old_auth.getLogin())).willReturn(Optional.of(auth1));

        authService.update(old_auth.getLogin(), auth1);
        old_auth = authService.readById(old_auth.getLogin()).get();
        Assertions.assertEquals(auth1, old_auth);
    }

    @Test
    void delete() {
        doNothing().when(authRepository).deleteById(auth1.getLogin());
        BDDMockito.given(authRepository.findById(auth1.getLogin())).willReturn(Optional.empty());

        authService.delete(auth1.getLogin());
        Optional<Auth> res = authService.readById(auth1.getLogin());
        Assertions.assertEquals(Optional.empty(), res);
    }
}