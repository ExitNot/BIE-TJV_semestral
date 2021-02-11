package cz.cvut.fit.tjv.semestral.business;

import cz.cvut.fit.tjv.semestral.data.AuthRepository;
import cz.cvut.fit.tjv.semestral.data.entities.Auth;
import cz.cvut.fit.tjv.semestral.data.entities.Book;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;

    public AuthServiceImpl(AuthRepository authRepository){ this.authRepository = authRepository; }

    @Override
    public Auth create(Auth data) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id");
        Example<Auth> example = Example.of(data);

        if( !authRepository.findAll(example).isEmpty() ){
            throw new ExistingEntityException();
        }
        return authRepository.save(data);
    }

    @Override
    public Optional<Auth> readById(String id) {
        return authRepository.findById(id);
    }

    @Override
    public Page<Auth> readAll(Pageable pageable) {
        return authRepository.findAll(pageable);
    }

    @Override
    public void update(String id, Auth newData) {
        authRepository.save(newData);
    }

    @Override
    public void delete(String id) {
        authRepository.deleteById(id);
    }
}
