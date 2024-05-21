package karuna.karuna_backend.receiver.domain;

import karuna.karuna_backend.receiver.dto.ReceiverDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

public class InMemoryReceiverRepository implements ReceiverRepository {

    private HashMap<Integer, Receiver> database = new HashMap<>();

    @Override
    public ReceiverDTO findByEmailIgnoreCase(String email) {
        return null;
    }

    @Override
    public void deleteByEmailIgnoreCase(String email) {

    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Receiver> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Receiver> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Receiver> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Receiver getOne(Integer integer) {
        return null;
    }

    @Override
    public Receiver getById(Integer integer) {
        return null;
    }

    @Override
    public Receiver getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Receiver> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Receiver> List<S> findAll(Example<S> example) {
        return new ArrayList<>();
    }

    @Override
    public <S extends Receiver> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Receiver> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Receiver> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Receiver> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Receiver, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Receiver> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Receiver> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Receiver> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Receiver> findAll() {
        return null;
    }

    @Override
    public List<Receiver> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Receiver entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Receiver> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Receiver> findAll(Sort sort) {
        database.put(1, new Receiver(1, "testEmail@gmail.com"));
        database.put(2, new Receiver(2, "testEmail2@gmail.com"));
        return database.values().stream().toList();
    }

    @Override
    public Page<Receiver> findAll(Pageable pageable) {
        return null;
    }
}
