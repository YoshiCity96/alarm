/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.database;

import java.util.ArrayList;
import lab_05.events.listeners.alarms.AlarmHM;
import lab_05.events.listeners.alarms.AlarmHMS;
import lab_05.events.listeners.alarms.IAlarm;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author AlibekovMurad5202
 */
public class DBManager {
    public void insertAlarm(IAlarm alarm) {
        Session session = DBSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(alarm);
        transaction.commit();
        session.close();
    }

    public void deleteAlarm(IAlarm alarm) {
        Session session = DBSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(alarm);
        transaction.commit();
        session.close();
    }
    
    public ArrayList<IAlarm> fetchAlarmsHM() {
        Session session = DBSessionFactory.getSessionFactory().openSession();
        ArrayList<IAlarm> alarms = new ArrayList<>();
        alarms.addAll(session.createQuery("from Alarms_HM", AlarmHM.class).getResultList());
        session.close();
        return alarms;
    }
    
    public ArrayList<IAlarm> fetchAlarmsHMS() {
        Session session = DBSessionFactory.getSessionFactory().openSession();
        ArrayList<IAlarm> alarms = new ArrayList<>();
        alarms.addAll(session.createQuery("from Alarms_HMS", AlarmHMS.class).getResultList());
        session.close();
        return alarms;
    }
}
