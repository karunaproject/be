package karuna.karuna_backend.content.domain;

import karuna.karuna_backend.Constants;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

class InMemoryContentRepository implements ContentRepository{

    private HashMap<Long, Content> database = new HashMap<>();

    @Override
    public List<Content> findByPageIgnoreCaseOrPageNull(String page) {
        return database.values().stream()
                .filter(entity -> Objects.equals(entity.getPage(), page))
                .toList();
    }

    @Override
    public Optional<Content> getByPageAndKey(String page, String key) {
        return database.values().stream()
                .filter(entity -> Objects.equals(entity.getPage(), page) && Objects.equals(entity.getKey(), key))
                .findFirst();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Content> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Content> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Content> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Content getOne(Long aLong) {
        return null;
    }

    @Override
    public Content getById(Long aLong) {
        return null;
    }

    @Override
    public Content getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Content> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Content> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Content> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Content> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Content> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Content> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Content, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Content> S save(S entity) {
        long id = database.size() + 1;
        if (entity.getId() == null) {
            entity.setId(id);
        } else {
            id = entity.getId();
        }
        database.put(id, entity);
        return entity;
    }

    @Override
    public <S extends Content> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Content> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Content> findAll() {
        return null;
    }

    @Override
    public List<Content> findAllById(Iterable<Long> longs) {
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
    public void delete(Content entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Content> entities) {

    }

    @Override
    public void deleteAll() {
        database.clear();
    }

    @Override
    public List<Content> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Content> findAll(Pageable pageable) {
        return null;
    }
}
