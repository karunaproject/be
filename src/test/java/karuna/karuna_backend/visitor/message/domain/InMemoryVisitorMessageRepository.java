package karuna.karuna_backend.visitor.message.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

class InMemoryVisitorMessageRepository implements VisitorMessageRepository {

    private HashMap<Long, VisitorMessage> database = new HashMap<>();

    @Override
    public void flush() {

    }

    @Override
    public <S extends VisitorMessage> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends VisitorMessage> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<VisitorMessage> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public VisitorMessage getOne(Long aLong) {
        return null;
    }

    @Override
    public VisitorMessage getById(Long aLong) {
        return null;
    }

    @Override
    public VisitorMessage getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends VisitorMessage> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends VisitorMessage> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends VisitorMessage> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends VisitorMessage> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends VisitorMessage> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends VisitorMessage> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends VisitorMessage, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends VisitorMessage> S save(S entity) {
        long id = database.size() + 1;
        if (entity.getID() == 0) {
            entity.setID(id);
        } else {
            id = entity.getID();
        }
        if (Objects.isNull(entity.getCreatedAt())) {
            entity.setCreatedAt(OffsetDateTime.now());
        }
        database.put(id, entity);
        return entity;
    }

    @Override
    public <S extends VisitorMessage> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<VisitorMessage> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<VisitorMessage> findAll() {
        return null;
    }

    @Override
    public List<VisitorMessage> findAllById(Iterable<Long> longs) {
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
    public void delete(VisitorMessage entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends VisitorMessage> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<VisitorMessage> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<VisitorMessage> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<VisitorMessage> findAllByOrderByCreatedAtDesc(PageRequest pageRequest) {
        return null;
    }
}