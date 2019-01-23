package pl.dopierala.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.dopierala.domain.BookCopy;
import pl.dopierala.domain.BookDefinition;
import pl.dopierala.service.BooksService;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BooksService bookService;

    @RequestMapping(value = "/BookCopy/{id}",method = RequestMethod.GET)
    @ResponseBody
    public BookCopy getBookCopy(@PathVariable("id") Integer id){
        BookCopy bookCopyById = bookService.getBookCopyById(id);
        return bookCopyById;
    }

    @RequestMapping(value = "/Book/{id}",method = RequestMethod.GET)
    @ResponseBody
    public BookDefinition getBookDefinition(@PathVariable("id") Integer id){
        BookDefinition bookDefinitionById = bookService.getBookDefinitionById(id);
        return bookDefinitionById;
    }

    @RequestMapping(value = "/Books",method = RequestMethod.GET)
    @ResponseBody
    public List<BookDefinition> getBooksDefinitions(){
        List<BookDefinition> booksDefinitions = bookService.getAllBookDefinitions();
        return booksDefinitions;
    }

    @RequestMapping(value = "/home")
    @ResponseBody
    public String testMapping(){
        return "<html>To jest tekst</html>";
    }
}
