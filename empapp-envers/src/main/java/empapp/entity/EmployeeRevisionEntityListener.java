package empapp.entity;

import org.hibernate.envers.RevisionListener;

import java.time.LocalDateTime;
import java.util.UUID;

public class EmployeeRevisionEntityListener implements RevisionListener {

    @Override
    public void newRevision(Object o) {
        if (o instanceof EmployeeRevisionEntity entity) {
            entity.setUsername("admin");
            entity.setUuid(UUID.randomUUID().toString());
        }
    }
}
