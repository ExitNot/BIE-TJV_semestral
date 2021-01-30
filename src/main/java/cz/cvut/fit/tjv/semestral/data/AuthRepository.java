package cz.cvut.fit.tjv.semestral.data;

import cz.cvut.fit.tjv.semestral.data.entities.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth, String> {
}
