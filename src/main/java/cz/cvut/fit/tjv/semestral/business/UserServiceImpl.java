package cz.cvut.fit.tjv.semestral.business;

import cz.cvut.fit.tjv.semestral.data.UserRepository;
import cz.cvut.fit.tjv.semestral.data.entities.Auth;
import cz.cvut.fit.tjv.semestral.data.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User data) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id");
        Example<User> example = Example.of(data);

        if( !userRepository.findAll(example).isEmpty() ){
            throw new ExistingEntityException();
        }
        return userRepository.save(data);
    }

    @Override
    public Optional<User> readById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<User> readAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void update(User newData) {
        userRepository.save(newData);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
