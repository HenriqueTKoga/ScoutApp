package scoutapp.com.br.scoutapp.model;

import scoutapp.com.br.scoutapp.model.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "CHAMPIONSHIP".
 */
public class Championship implements java.io.Serializable {

    private Long id;
    private String champName;
    private String state;
    private String city;
    private long athleteId;
    private long gameUserId;
    private long gameOpponentId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient ChampionshipDao myDao;

    private Athlete athlete;
    private Long athlete__resolvedKey;

    private GameUser gameUser;
    private Long gameUser__resolvedKey;

    private GameOpponent gameOpponent;
    private Long gameOpponent__resolvedKey;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Championship() {
    }

    public Championship(Long id) {
        this.id = id;
    }

    public Championship(Long id, String champName, String state, String city, long athleteId, long gameUserId, long gameOpponentId) {
        this.id = id;
        this.champName = champName;
        this.state = state;
        this.city = city;
        this.athleteId = athleteId;
        this.gameUserId = gameUserId;
        this.gameOpponentId = gameOpponentId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getChampionshipDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChampName() {
        return champName;
    }

    public void setChampName(String champName) {
        this.champName = champName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(long athleteId) {
        this.athleteId = athleteId;
    }

    public long getGameUserId() {
        return gameUserId;
    }

    public void setGameUserId(long gameUserId) {
        this.gameUserId = gameUserId;
    }

    public long getGameOpponentId() {
        return gameOpponentId;
    }

    public void setGameOpponentId(long gameOpponentId) {
        this.gameOpponentId = gameOpponentId;
    }

    /** To-one relationship, resolved on first access. */
    public Athlete getAthlete() {
        long __key = this.athleteId;
        if (athlete__resolvedKey == null || !athlete__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AthleteDao targetDao = daoSession.getAthleteDao();
            Athlete athleteNew = targetDao.load(__key);
            synchronized (this) {
                athlete = athleteNew;
            	athlete__resolvedKey = __key;
            }
        }
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        if (athlete == null) {
            throw new DaoException("To-one property 'athleteId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.athlete = athlete;
            athleteId = athlete.getId();
            athlete__resolvedKey = athleteId;
        }
    }

    /** To-one relationship, resolved on first access. */
    public GameUser getGameUser() {
        long __key = this.gameUserId;
        if (gameUser__resolvedKey == null || !gameUser__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GameUserDao targetDao = daoSession.getGameUserDao();
            GameUser gameUserNew = targetDao.load(__key);
            synchronized (this) {
                gameUser = gameUserNew;
            	gameUser__resolvedKey = __key;
            }
        }
        return gameUser;
    }

    public void setGameUser(GameUser gameUser) {
        if (gameUser == null) {
            throw new DaoException("To-one property 'gameUserId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.gameUser = gameUser;
            gameUserId = gameUser.getId();
            gameUser__resolvedKey = gameUserId;
        }
    }

    /** To-one relationship, resolved on first access. */
    public GameOpponent getGameOpponent() {
        long __key = this.gameOpponentId;
        if (gameOpponent__resolvedKey == null || !gameOpponent__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GameOpponentDao targetDao = daoSession.getGameOpponentDao();
            GameOpponent gameOpponentNew = targetDao.load(__key);
            synchronized (this) {
                gameOpponent = gameOpponentNew;
            	gameOpponent__resolvedKey = __key;
            }
        }
        return gameOpponent;
    }

    public void setGameOpponent(GameOpponent gameOpponent) {
        if (gameOpponent == null) {
            throw new DaoException("To-one property 'gameOpponentId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.gameOpponent = gameOpponent;
            gameOpponentId = gameOpponent.getId();
            gameOpponent__resolvedKey = gameOpponentId;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
