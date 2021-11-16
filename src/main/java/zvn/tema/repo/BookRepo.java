package zvn.tema.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zvn.tema.dto.Book;

@Repository
public interface BookRepo extends CrudRepository<Book, String> {

}

