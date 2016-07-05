package scoutapp.com.br.scoutapp.model;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import scoutapp.com.br.scoutapp.model.Athlete;
import scoutapp.com.br.scoutapp.model.Championship;

import scoutapp.com.br.scoutapp.model.AthleteDao;
import scoutapp.com.br.scoutapp.model.ChampionshipDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig athleteDaoConfig;
    private final DaoConfig championshipDaoConfig;

    private final AthleteDao athleteDao;
    private final ChampionshipDao championshipDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        athleteDaoConfig = daoConfigMap.get(AthleteDao.class).clone();
        athleteDaoConfig.initIdentityScope(type);

        championshipDaoConfig = daoConfigMap.get(ChampionshipDao.class).clone();
        championshipDaoConfig.initIdentityScope(type);

        athleteDao = new AthleteDao(athleteDaoConfig, this);
        championshipDao = new ChampionshipDao(championshipDaoConfig, this);

        registerDao(Athlete.class, athleteDao);
        registerDao(Championship.class, championshipDao);
    }
    
    public void clear() {
        athleteDaoConfig.getIdentityScope().clear();
        championshipDaoConfig.getIdentityScope().clear();
    }

    public AthleteDao getAthleteDao() {
        return athleteDao;
    }

    public ChampionshipDao getChampionshipDao() {
        return championshipDao;
    }

}
