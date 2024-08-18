package karuna.karuna_backend.post.domain

import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.query.FluentQuery

import java.util.function.Function

class MockPostRepository implements PostRepository{
    
    private Map<Long, Post> database = [:]
    
    @Override
    void flush() {

    }

    @Override
    void deleteAllInBatch(Iterable<Post> entities) {

    }

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    void deleteAllInBatch() {

    }

    @Override
    Post getOne(Long aLong) {
        return null
    }

    @Override
    Post getById(Long aLong) {
        return null
    }

    @Override
    Post getReferenceById(Long aLong) {
        return null
    }

    @Override
    <S extends Post> List<S> findAll(Example<S> example, Sort sort) {
        return null
    }

    @Override
    <S extends Post> List<S> findAll(Example<S> example) {
        return null
    }

    @Override
    <S extends Post> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null
    }

    @Override
    <S extends Post> S saveAndFlush(S entity) {
        return null
    }

    @Override
    <S extends Post> List<S> saveAll(Iterable<S> entities) {
        return null
    }

    @Override
    <S extends Post> S save(S entity) {
        Long id = database.size() + 1
        entity.setID(id)
        database.put(id, entity)
        entity
    }

    @Override
    Optional<Post> findById(Long aLong) {
        return null
    }

    @Override
    boolean existsById(Long aLong) {
        return false
    }

    @Override
    List<Post> findAll() {
        return null
    }

    @Override
    List<Post> findAllById(Iterable<Long> longs) {
        return null
    }

    @Override
    long count() {
        return 0
    }

    @Override
    void deleteById(Long aLong) {

    }

    @Override
    void delete(Post entity) {

    }

    @Override
    void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    void deleteAll(Iterable<? extends Post> entities) {

    }

    @Override
    void deleteAll() {

    }

    @Override
    List<Post> findAll(Sort sort) {
        return null
    }

    @Override
    Page<Post> findAll(Pageable pageable) {
        return null
    }

    @Override
    <S extends Post> Optional<S> findOne(Example<S> example) {
        return null
    }

    @Override
    <S extends Post> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null
    }

    @Override
    <S extends Post> long count(Example<S> example) {
        return 0
    }

    @Override
    <S extends Post> boolean exists(Example<S> example) {
        return false
    }

    @Override
    <S extends Post, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null
    }
}
