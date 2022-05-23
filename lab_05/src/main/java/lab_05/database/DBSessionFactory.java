/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.database;

import lab_05.events.listeners.alarms.AlarmHM;
import lab_05.events.listeners.alarms.AlarmHMS;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author AlibekovMurad5202
 */
public class DBSessionFactory {
    private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .configure();
                
                configuration.addAnnotatedClass(AlarmHM.class);
                configuration.addAnnotatedClass(AlarmHMS.class);
                
                StandardServiceRegistryBuilder builder = 
                        new StandardServiceRegistryBuilder()
                                .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
