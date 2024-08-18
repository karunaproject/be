package karuna.karuna_backend.content.domain

import karuna.karuna_backend.content.domain.Content
import karuna.karuna_backend.content.domain.ContentRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.query.FluentQuery

import java.util.function.Function

class MockContentRepository implements ContentRepository {

    private Map<Long, Content> database = [:]

    @Override
    List<Content> findByPageIgnoreCaseOrPageNull(String page) {
        database.values().findAll { it.page?.equalsIgnoreCase(page) }
    }

    @Override
    Optional<Content> getByPageAndKey(String page, String key) {
        Optional.ofNullable(database.values().find { it.page == page && it.key == key })
    }

    @Override
    void flush() {
        // No-op for in-memory implementation
    }

    @Override
    <S extends Content> S saveAndFlush(S entity) {
        save(entity)
    }

    @Override
    <S extends Content> List<S> saveAllAndFlush(Iterable<S> entities) {
        saveAll(entities)
    }

    @Override
    void deleteAllInBatch(Iterable<Content> entities) {
        entities.each { delete(it) }
    }

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs) {
        longs.each { deleteById(it) }
    }

    @Override
    void deleteAllInBatch() {
        deleteAll()
    }

    @Override
    Content getOne(Long aLong) {
        database[aLong]
    }

    @Override
    Content getById(Long aLong) {
        database[aLong]
    }

    @Override
    Content getReferenceById(Long aLong) {
        getById(aLong)
    }

    @Override
    <S extends Content> Optional<S> findOne(Example<S> example) {
        Optional.empty()
    }

    @Override
    <S extends Content> List<S> findAll(Example<S> example) {
        []
    }

    @Override
    <S extends Content> List<S> findAll(Example<S> example, Sort sort) {
        []
    }

    @Override
    <S extends Content> Page<S> findAll(Example<S> example, Pageable pageable) {
        Page.empty()
    }

    @Override
    <S extends Content> long count(Example<S> example) {
        0
    }

    @Override
    <S extends Content> boolean exists(Example<S> example) {
        false
    }

    @Override
    <S extends Content, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        null
    }

    @Override
    <S extends Content> S save(S entity) {
        long id = entity.id ?: (database.size() + 1)
        entity.id = id
        database[id] = entity
        entity
    }

    @Override
    <S extends Content> List<S> saveAll(Iterable<S> entities) {
        entities.collect { save(it) }
    }

    @Override
    Optional<Content> findById(Long aLong) {
        Optional.ofNullable(database[aLong])
    }

    @Override
    boolean existsById(Long aLong) {
        database.containsKey(aLong)
    }

    @Override
    List<Content> findAll() {
        database.values().toList()
    }

    @Override
    List<Content> findAllById(Iterable<Long> longs) {
        longs.collect { database[it] }
    }

    @Override
    long count() {
        database.size()
    }

    @Override
    void deleteById(Long aLong) {
        database.remove(aLong)
    }

    @Override
    void delete(Content entity) {
        database.remove(entity.id)
    }

    @Override
    void deleteAllById(Iterable<? extends Long> longs) {
        longs.each { deleteById(it) }
    }

    @Override
    void deleteAll(Iterable<? extends Content> entities) {
        entities.each { delete(it) }
    }

    @Override
    void deleteAll() {
        database.clear()
    }

    @Override
    List<Content> findAll(Sort sort) {
        findAll()
    }

    @Override
    Page<Content> findAll(Pageable pageable) {
        Page.empty()
    }
}
