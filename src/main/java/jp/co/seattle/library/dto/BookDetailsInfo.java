package jp.co.seattle.library.dto;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 書籍詳細情報格納DTO
 *
 */
@Configuration
@Data
public class BookDetailsInfo {

    private int bookId;

    private String title;

    private String author;

    private String publisher;

    private String thumbnailUrl;

    private String thumbnailName;

    private String publishDate;

    private String description;

    private String isbn;


    public BookDetailsInfo() {
        // ＠があることで、セッターとゲッターがいらない
    }

    public BookDetailsInfo(int bookId, String title, String author, String publisher, String publishDate,
            String description, String isbn,
            String thumbnailUrl, String thumbnailName) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.thumbnailUrl = thumbnailUrl;
        this.thumbnailName = thumbnailName;
        this.description = description;
        this.isbn = isbn;
    }

}