package pl.dopierala.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.dopierala.domain.BookCopy;
import pl.dopierala.domain.BookDefinition;
import pl.dopierala.service.BooksService;

import java.io.*;

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
        BookDefinition bookCopyById = bookService.getBookDefinitionById(id);
        return bookCopyById;
    }

    @RequestMapping(value = "/home")
    @ResponseBody
    public String testMapping(){
        return "<html>To jest tekst</html>";
    }
}
