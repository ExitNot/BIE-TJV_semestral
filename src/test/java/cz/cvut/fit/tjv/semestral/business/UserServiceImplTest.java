package cz.cvut.fit.tjv.semestral.business;

import cz.cvut.fit.tjv.semestral.data.UserRepository;
import cz.cvut.fit.tjv.semestral.data.entities.User;
import cz.cvut.fit.tjv.semestral.data.entities.User;
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

@SpringBootTest(classes = {cz.cvut.fit.tjv.semestral.business.UserServiceImpl.class})
class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;
    @MockBean
    private UserRepository userRepository;

    private final User usr1 = new User( null, "Vaclav",
            "vaclav@mr.cz", "Writer", null);
    private final User usr2 = new User( null, "Pepa",
            "pepajo@mr.cz", "Painter", null);

    @Test
    void create() {
        Example<User> book_ex = Example.of(usr1);

        BDDMockito.given(userRepository.findAll(book_ex)).willReturn(Collections.emptyList());
        BDDMockito.given(userRepository.save(usr1)).willReturn(usr1);

        User result = userService.create(usr1);
        Assertions.assertEquals(usr1, result);
    }

    @Test
    void readById() {
        Optional<User> optional_book1 = Optional.of(usr1);

        BDDMockito.given(userRepository.findById(usr1.getId())).willReturn(optional_book1);

        Optional<User> result = userService.readById(usr1.getId());
        Assertions.assertEquals(optional_book1.get(), result.get());
    }

    @Test
    void readAll() {
        final List<User> data = List.of(usr1, usr2);
        final Pageable pageable = PageRequest.of( 0, 2);
        final Page<User> pageExpected = new PageImpl<>(data, pageable, 3);

        BDDMockito.given(userRepository.findAll(pageable)).willReturn(pageExpected);

        Assertions.assertEquals(pageExpected ,userService.readAll(pageable));
    }

    @Test
    void update() {
        User old_user = usr1;
        old_user.setUserType("---------");

        doNothing().when(userRepository).deleteById(old_user.getId());
        BDDMockito.given(userRepository.save(usr2)).willReturn(usr2); //userRepository.findById(id)
        BDDMockito.given(userRepository.findById(old_user.getId())).willReturn(Optional.of(usr1));

        userService.update(old_user.getId(), usr1);
        old_user = userService.readById(old_user.getId()).get();
        Assertions.assertEquals(usr1, old_user);
    }

    @Test
    void delete() {
        doNothing().when(userRepository).deleteById(usr1.getId());
        BDDMockito.given(userRepository.findById(usr1.getId())).willReturn(Optional.empty());

        userService.delete(usr1.getId());
        Optional<User> res = userService.readById(usr1.getId());
        Assertions.assertEquals(Optional.empty(), res);
    }
}