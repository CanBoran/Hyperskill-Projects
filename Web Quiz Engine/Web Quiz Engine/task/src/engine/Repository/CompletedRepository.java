package engine.Repository;

import engine.Model.Completed;
import engine.Model.Question;
import engine.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompletedRepository extends PagingAndSortingRepository<Completed, Integer> {

    @Query("select c from Completed c where c.user.Id = :id order by c.completedAt desc")
    Page<Completed> findAllByUserId(@Param("id") int id, Pageable pageable);
}
