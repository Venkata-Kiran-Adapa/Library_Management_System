package com.Library_Management_System.Service;

import com.Library_Management_System.Entity.Books;
import com.Library_Management_System.Enums.Categories;
import com.Library_Management_System.Exception_Handling.NotFoundException;
import com.Library_Management_System.Repository.BooksRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BooksRepo booksRepo;
    @Autowired
    ObjectMapper objectMapper;

    public Optional<Books> getById(long bookId) throws NotFoundException {
        if(booksRepo.existsById(bookId)){
            return  booksRepo.findById(bookId);
        }
        throw new NotFoundException("Book With Id: "+bookId+" is not Found");
    }

    public Optional<Books> getBookByIsBn(String isbn) throws NotFoundException {
        Optional<Books> book=booksRepo.findByIsbn(isbn);
        if(book.isEmpty()){
            throw new NotFoundException("Book With IsBn: "+isbn+" is not Found");
        }
        return  book;
    }

    public List<Books> getAllBooks(){
        return booksRepo.findAll();
    }
     
    public List<Books> getAllAvailableBooks(){
    	 return booksRepo.findByAvailableTrue();
    }
     
    
    public Books addBook(@RequestBody Books book){
        return booksRepo.save(book);
    }

    public Books modifyBook(long id, Map<String,Object> partialBook) throws NotFoundException {
        Optional<Books> book=booksRepo.findById(id);
        if(book.isEmpty()){
            throw new NotFoundException("User With Id: "+id+" is not Found");
        }
        if(partialBook.containsKey("book_id")){
            throw new NotFoundException("Primary Key Changing Error - Custom One");
        }
        Books patchedBook=apply(book,partialBook);
        Books dbBook=booksRepo.save(patchedBook);
        return dbBook;
    }

    private Books apply(Optional<Books> tempEmployee, Map<String, Object> patchPayLoad) {

        ObjectNode tempObjectNode=objectMapper.convertValue(tempEmployee,ObjectNode.class);
        ObjectNode patchPayLoadNode=objectMapper.convertValue(patchPayLoad, ObjectNode.class);
        tempObjectNode.setAll(patchPayLoadNode);
        return objectMapper.convertValue(tempObjectNode, Books.class);
    }

    public Optional<Books> deleteBookById(long id) throws NotFoundException {
        if(booksRepo.existsById(id)) {
            Optional<Books> book = booksRepo.findById(id);
            booksRepo.deleteById(id);
            return book;
        }
        throw new NotFoundException("Book With Id: "+id+" is not Found");
    }

    public List<Books> getBooksByCategories(Categories category) {
        return booksRepo.findAllByCategory(category);
    }

	public List<Books> getBookByAuthor(String author) {
		return booksRepo.findAllByBookAuthor(author);
	}

}
