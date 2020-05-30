package exam.demo.repository.kanselyariya;

import exam.demo.entity.hujjat.Hujjat;
import exam.demo.entity.hujjat.HujjatTanishish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HujjatTanishishRepository extends JpaRepository<HujjatTanishish,Long> {
}
