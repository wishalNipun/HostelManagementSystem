package lk.ijse.hibernate.dao;

import lk.ijse.hibernate.entity.Student;

import java.io.IOException;
import java.util.List;

public interface CrudDAO<Entity,ID>extends SuperDAO {
    boolean save(Entity entity) throws Exception;

    boolean update(Entity entity) throws Exception;

    boolean delete(ID id) throws Exception;

    List<Entity> getAll() throws IOException;

    String generateId() throws Exception;



}
