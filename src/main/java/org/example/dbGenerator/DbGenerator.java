package org.example.dbGenerator;

import com.opencsv.bean.CsvToBeanBuilder;
import org.example.HibernateFactory;
import org.example.dao.EntityDao;
import org.example.model.Author;
import org.example.model.Movie;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class DbGenerator {

    private static final Path AUTHOR_FILE_PATH = Path.of("src", "main", "resources", "Authors.csv");
    private static final Path MOVIE_FILE_PATH = Path.of("src", "main", "resources", "Movies.csv");

    public static void main(String[] args) throws IOException {
        HibernateFactory hibernateFactory = new HibernateFactory();
        EntityDao<Author> authorDao = new EntityDao<>(hibernateFactory, Author.class);
        EntityDao<Movie> movieDao = new EntityDao<>(hibernateFactory, Movie.class);

        loadAuthos(authorDao);

        List<org.example.dbgenerator.MovieCsv> movieCsvList = new CsvToBeanBuilder(new FileReader(MOVIE_FILE_PATH.toFile()))
                .withType(org.example.dbgenerator.MovieCsv.class)
                .build()
                .parse();
        movieCsvList.stream()
                .map(DbGenerator::toDbMovie)
                .forEach(movieDao::save);

        hibernateFactory.getSessionFactory().close();
    }

    private static Movie toDbMovie(org.example.dbgenerator.MovieCsv movieCsv) {
        Movie movie = new Movie();
        movie.setTitle(movieCsv.getMovie());
        movie.setType(movieCsv.getType());
        movie.setTime(movieCsv.getTime());
        movie.setProductionYear(movieCsv.getYear());
        return movie;
    }

    private static void loadAuthos(EntityDao<Author> authorDao) throws FileNotFoundException {
        List<org.example.dbgenerator.AuthorCsv> authorCsvList = new CsvToBeanBuilder(new FileReader(AUTHOR_FILE_PATH.toFile()))
                .withType(org.example.dbgenerator.AuthorCsv.class)
                .build()
                .parse();
        authorCsvList.stream()
                .map(DbGenerator::toDbAuthor)
                        .forEach(authorDao::save);
    }

    private static Author toDbAuthor(org.example.dbgenerator.AuthorCsv authorCsv) {
        Author author = new Author();
        author.setName(authorCsv.getFirstName());
        author.setSurname(authorCsv.getLastName());
        author.setAge(authorCsv.getAge());
        author.setNationality(authorCsv.getNationality());
        return author;
    }
}
