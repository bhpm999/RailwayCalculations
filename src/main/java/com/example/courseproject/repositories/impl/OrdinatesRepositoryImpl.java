package com.example.courseproject.repositories.impl;

import com.example.courseproject.GUI.Alerts;
import com.example.courseproject.domain.Entities.RailwayEntity;
import com.example.courseproject.repositories.OrdinatesRepository;
import com.example.courseproject.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.NoResultException;

public class OrdinatesRepositoryImpl implements OrdinatesRepository {
    private final Session session;
    private final RailwayEntity railwayEntity;
    private Transaction transaction;

    public OrdinatesRepositoryImpl(RailwayEntity railwayEntity){
        this.railwayEntity = railwayEntity;
        this.session = HibernateUtil.getSession();  // Инициализация сессии один раз
    }

    private void startTransaction() {
        if (transaction == null || !transaction.isActive()) {
            transaction = session.beginTransaction();
        }
    }

    @Override
    public int readU() {
        int U = 0;
        try {
            startTransaction();
            U = (int) session.createNativeQuery("SELECT U FROM rails WHERE railsType = :railsType " +
                            "AND epures = :epures " +
                            "AND sleepersType = :sleepersType " +
                            "AND ballastType = :ballastType")
                    .setParameter("railsType", railwayEntity.getRailsTypeForDB())
                    .setParameter("epures", railwayEntity.getEpures())
                    .setParameter("sleepersType", railwayEntity.getSleepersTypeForDB())
                    .setParameter("ballastType", railwayEntity.getBallastTypeForDB())
                    .getSingleResult();
            transaction.commit();
        } catch (NoResultException e) {
            Alerts.notExistAlert();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return U;
    }

    @Override
    public double readK(){
        double K = 0;
        try {
            startTransaction();
            K = (double) session.createNativeQuery("SELECT K FROM rails WHERE railsType = :railsType " +
                            "AND epures = :epures " +
                            "AND sleepersType = :sleepersType " +
                            "AND ballastType = :ballastType")
                    .setParameter("railsType", railwayEntity.getRailsTypeForDB())
                    .setParameter("epures", railwayEntity.getEpures())
                    .setParameter("sleepersType", railwayEntity.getSleepersTypeForDB())
                    .setParameter("ballastType", railwayEntity.getBallastTypeForDB())
                    .getSingleResult();
            transaction.commit();
        } catch (NoResultException e) {
            Alerts.notExistAlert();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return K;
    }
}
