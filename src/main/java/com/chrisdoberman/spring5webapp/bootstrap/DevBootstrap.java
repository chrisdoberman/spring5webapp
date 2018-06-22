package com.chrisdoberman.spring5webapp.bootstrap;

import com.chrisdoberman.spring5webapp.model.Author;
import com.chrisdoberman.spring5webapp.model.Book;
import com.chrisdoberman.spring5webapp.model.Publisher;
import com.chrisdoberman.spring5webapp.repositories.AuthorRepository;
import com.chrisdoberman.spring5webapp.repositories.BookRepository;
import com.chrisdoberman.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        // Vince
        Author vince = new Author("Vince", "Flynn");
        Publisher pb = new Publisher("Pocket Books", "7 Main Street", "Alexandria", "VA", "22301");
        Book top = new Book("Transfer of Power", "1234", pb);
        vince.getBooks().add(top);
        top.getAuthors().add(vince);

        publisherRepository.save(pb);
        authorRepository.save(vince);
        bookRepository.save(top);


        // Lee
        Author lee = new Author("Lee", "Child");
        Publisher dp = new Publisher("Dell Publishing", "10 Apple Street", "Baltimore", "MD", "21221");
        Book mm = new Book("Make Me", "5678", dp);
        lee.getBooks().add(mm);
        mm.getAuthors().add(lee);

        publisherRepository.save(dp);
        authorRepository.save(lee);
        bookRepository.save(mm);

    }
}
