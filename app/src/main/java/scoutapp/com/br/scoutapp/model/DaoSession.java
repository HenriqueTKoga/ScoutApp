package scoutapp.com.br.scoutapp.model;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import scoutapp.com.br.scoutapp.model.Athlete;

import scoutapp.com.br.scoutapp.model.AthleteDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig athleteDaoConfig;

    private final AthleteDao athleteDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        athleteDaoConfig = daoConfigMap.get(AthleteDao.class).clone();
        athleteDaoConfig.initIdentityScope(type);

        athleteDao = new AthleteDao(athleteDaoConfig, this);

        registerDao(Athlete.class, athleteDao);
    }
    
    public void clear() {
        athleteDaoConfig.getIdentityScope().clear();
    }

    public AthleteDao getAthleteDao() {
        return athleteDao;
    }

}
