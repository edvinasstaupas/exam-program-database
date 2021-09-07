package dao;

import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class AbstractDao<T> {
    protected final Session session;
    private final Class<T> entityClass;
    protected final String className;

    public AbstractDao(Session session, Class<T> entityClass) {
        this.session = session;
        this.entityClass = entityClass;
        this.className = entityClass.getSimpleName();
    }

    public List<T> loadAll() {
        return session.createQuery("from " + className, entityClass).list();
    }

    public Optional<T> getByID(Long id) {
        return Optional.ofNullable(session.get(entityClass, id));
    }

    public void saveOrUpdate(T newOrUpdatedObject) {
        session.beginTransaction();
        session.saveOrUpdate(newOrUpdatedObject);
        session.getTransaction().commit();
    }
    public void save(T newObject) {
        session.beginTransaction();
        session.save(newObject);
        session.getTransaction().commit();
    }

    public void delete(Long id) {
        session.beginTransaction();
        getByID(id)
                .ifPresent(session::delete);
        session.getTransaction().commit();
    }

    public Long getMaxID() {
        //noinspection JpaQlInspection
        return session.
                createQuery("select max(id) FROM " + className, Long.class)
                .list().get(0);
    }
}
