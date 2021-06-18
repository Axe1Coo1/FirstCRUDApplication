import models.Auto;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.*;

import javax.persistence.TypedQuery;
import javax.security.auth.login.AppConfigurationEntry;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SessionObjectsTest {
    private SessionFactory sessionFactory;
    private Session session;

    @Before
    public void before() {
        //setup session factory
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Auto.class);
        configuration.addAnnotatedClass(User.class);
        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
        session = sessionFactory.openSession();
    }

    @Test
    public void returnsAutosWithMatchingType() {
        //Create objects needed for testing


        Auto Ford = new Auto("F - 100", "green");

        User Axel = new User("Axel", 22);

        Ford.setUser(Axel);
        Axel.addAuto(Ford);

        session.save(Axel);
        session.save(Ford);


        assertNotNull(session);
        assertEquals("F - 100", Ford.getModel());
        assertEquals("Axel", Axel.getName());








    }

    @After
    public void after(){
        session.close();
        sessionFactory.close();
    }
}


