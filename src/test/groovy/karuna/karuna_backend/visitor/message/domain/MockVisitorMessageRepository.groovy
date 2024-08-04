package karuna.karuna_backend.visitor.message.domain

import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.FluentQuery

import java.util.function.Function

class MockVisitorMessageRepository implements VisitorMessageRepository {

    Map<Long,VisitorMessage> database = [:]

    @Override
    Page<VisitorMessage> findAll(Pageable pageable) {
        List<VisitorMessage> visitorMessages = new ArrayList<>(database.values());

        if (pageable.getSort() != null) {
            Sort sort = pageable.getSort();
            Comparator<VisitorMessage> comparator = null;

            for (Sort.Order order : sort) {
                Comparator<VisitorMessage> orderComparator = Comparator.comparing(vm -> {
                    switch (order.getProperty()) {
                        case "ID":
                            return vm.getID();
                        case "createdAt":
                            return vm.getCreatedAt();
                        case "body":
                            return vm.getBody();
                        case "contact":
                            return vm.getContact();
                        default:
                            throw new IllegalArgumentException("Unknown property: " + order.getProperty());
                    }
                });

                if (order.isDescending()) {
                    orderComparator = orderComparator.reversed();
                }

                if (comparator == null) {
                    comparator = orderComparator;
                } else {
                    comparator = comparator.thenComparing(orderComparator);
                }
            }

            if (comparator != null) {
                visitorMessages = visitorMessages.stream().sorted(comparator).collect(Collectors.toList());
            }
        }

        // Apply pagination
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<VisitorMessage> list;

        if (visitorMessages.size() < startItem) {
            list = new ArrayList<>();
        } else {
            int toIndex = Math.min(startItem + pageSize, visitorMessages.size());
            list = visitorMessages.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), visitorMessages.size());
    }


    @Override
    void flush() {

    }

    @Override
    void deleteAllInBatch(Iterable<VisitorMessage> entities) {

    }

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    void deleteAllInBatch() {

    }

    @Override
    VisitorMessage getOne(Long aLong) {
        return null
    }

    @Override
    VisitorMessage getById(Long aLong) {
        return null
    }

    @Override
    VisitorMessage getReferenceById(Long aLong) {
        return null
    }

    @Override
    <S extends VisitorMessage> List<S> findAll(Example<S> example, Sort sort) {
        return null
    }

    @Override
    <S extends VisitorMessage> List<S> findAll(Example<S> example) {
        return null
    }

    @Override
    <S extends VisitorMessage> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null
    }

    @Override
    <S extends VisitorMessage> S saveAndFlush(S entity) {
        return null
    }

    @Override
    <S extends VisitorMessage> List<S> saveAll(Iterable<S> entities) {
        return null
    }

    @Override
    <S extends VisitorMessage> S save(S entity) {
        Long id = database.size() + 1
        entity.setID(id)
        database.put(id, entity)
        entity
    }

    @Override
    Optional<VisitorMessage> findById(Long aLong) {
        return null
    }

    @Override
    boolean existsById(Long aLong) {
        return false
    }

    @Override
    List<VisitorMessage> findAll() {
        return null
    }

    @Override
    List<VisitorMessage> findAllById(Iterable<Long> longs) {
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
    void delete(VisitorMessage entity) {

    }

    @Override
    void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    void deleteAll(Iterable<? extends VisitorMessage> entities) {

    }

    @Override
    void deleteAll() {
        database = [:]
    }

    @Override
    List<VisitorMessage> findAll(Sort sort) {
        return null
    }

    @Override
    <S extends VisitorMessage> Optional<S> findOne(Example<S> example) {
        return null
    }

    @Override
    <S extends VisitorMessage> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null
    }

    @Override
    <S extends VisitorMessage> long count(Example<S> example) {
        return 0
    }

    @Override
    <S extends VisitorMessage> boolean exists(Example<S> example) {
        return false
    }

    @Override
    <S extends VisitorMessage, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null
    }
}
