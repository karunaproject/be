package karuna.karuna_backend.post.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

class InMemoryPostRepository implements PostRepository {

    @Override
    public void flush() {

    }

    @Override
    public <S extends Post> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Post> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Post> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Post getOne(Long aLong) {
        return null;
    }

    @Override
    public Post getById(Long aLong) {
        return null;
    }

    @Override
    public Post getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Post> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Post> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Post> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Post> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Post> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Post> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Post, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Post> S save(S entity) {
        return (S) new Post(1L, OffsetDateTime.now(), entity.getBody(), entity.getAuthor());
    }

    @Override
    public <S extends Post> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Post> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public List<Post> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Post entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Post> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Post> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return null;
    }
}