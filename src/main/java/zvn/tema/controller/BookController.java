package zvn.tema.controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zvn.tema.dto.Book;
import zvn.tema.service.BookService;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value="/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public String add(@RequestBody Book book) {
        var result = new JSONObject();
        switch(bookService.add(book)){
            default:
                result.put("error","Unknown error");break;
            case ok:
                result.put("success","1");break;
            case alreadyPresent:
                result.put("error","Book already exists");break;
        }
        return result.toString();
    }

    @GetMapping(value="/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public String list(@RequestParam(name = "limit", defaultValue = "10") int limit) {
        var result=new JSONObject();
        JSONArray books = new JSONArray();
        for(var book: bookService.list(limit)){
            books.add(book);
        }
        result.put("books",books);
        return result.toString();
    }

    @GetMapping(value="/get/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String get(@PathVariable(value="itemId") String itemId) {
        var result=new JSONObject();
        switch(bookService.exists(itemId)){
            default:
                result.put("error","Unknown error");break;
            case ok:
                var book = bookService.get(itemId);
                result.put("id",book.getId());
                result.put("name",book.getName());
                result.put("author",book.getAuthor());
                break;
            case notPresent:
                result.put("error","Book does not exists");break;
        }
        return result.toString();
    }

    @PutMapping(value = "/update/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@PathVariable(value="itemId") String itemId,@RequestBody Book book){
        var result = new JSONObject();
        switch(bookService.update(itemId,book)){
            default:
                result.put("error","Unknown error");break;
            case ok:
                result.put("success","1");break;
            case notPresent:
                result.put("error","Book does not exists");break;
        }
        return result.toString();
    }

    @DeleteMapping(value = "/delete/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable(value="itemId") String itemId){
        var result = new JSONObject();
        switch(bookService.delete(itemId)){
            default:
                result.put("error","Unknown error");break;
            case ok:
                result.put("success","1");break;
            case notPresent:
                result.put("error","Book does not exists");break;
        }
        return result.toString();
    }

}
