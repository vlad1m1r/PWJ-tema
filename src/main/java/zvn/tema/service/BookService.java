package zvn.tema.service;

import org.springframework.stereotype.Service;
import zvn.tema.config.config.BookRepoErr;
import zvn.tema.dto.Book;
import zvn.tema.repo.BookRepo;

import java.util.LinkedList;
import java.util.List;

@Service
public class BookService {
    private final BookRepo bookRepo;

    public BookService(BookRepo itemRepo) {
        this.bookRepo = itemRepo;
    }

    public BookRepoErr add(Book book){
        if(bookRepo.existsById(book.getId())){
            return BookRepoErr.alreadyPresent;
        }
        bookRepo.save(book);
        return BookRepoErr.ok;
    }

    public Book get(String id){
        return bookRepo.findById(id).get();
    }

    public BookRepoErr exists(String id){
        return bookRepo.findById(id).isPresent()?BookRepoErr.ok:BookRepoErr.notPresent;
    }

    public BookRepoErr update(String id,Book book){
        if(bookRepo.findById(id).isPresent()){
            if (id.compareTo(book.getId()) != 0) {
                bookRepo.deleteById(id);
            }
            bookRepo.save(book);
            return BookRepoErr.ok;
        }else {
            return BookRepoErr.notPresent;
        }
    }

    public BookRepoErr delete(String id){
        try {
            bookRepo.deleteById(id);
            return BookRepoErr.ok;
        }catch (Exception ex) {
            return BookRepoErr.notPresent;
        }
    }


    public List<Book> list(int limit){
        List<Book> books=new LinkedList<Book>();
        for(var book: bookRepo.findAll()){
            if(limit==0)break;
            books.add(book);
            limit--;
        }
        return books;
    }
}
