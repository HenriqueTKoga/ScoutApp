package scoutapp.com.br.scoutapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

import scoutapp.com.br.scoutapp.model.AthleteDao;
import scoutapp.com.br.scoutapp.model.ChampionshipDao;
import scoutapp.com.br.scoutapp.model.UserDao;
import scoutapp.com.br.scoutapp.model.GameUserDao;
import scoutapp.com.br.scoutapp.model.GameOpponentDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * Master of DAO (schema version 4): knows all DAOs.
*/
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 4;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        AthleteDao.createTable(db, ifNotExists);
        ChampionshipDao.createTable(db, ifNotExists);
        UserDao.createTable(db, ifNotExists);
        GameUserDao.createTable(db, ifNotExists);
        GameOpponentDao.createTable(db, ifNotExists);
    }
    
    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {
        AthleteDao.dropTable(db, ifExists);
        ChampionshipDao.dropTable(db, ifExists);
        UserDao.dropTable(db, ifExists);
        GameUserDao.dropTable(db, ifExists);
        GameOpponentDao.dropTable(db, ifExists);
    }
    
    public static abstract class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }
    
    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

    public DaoMaster(SQLiteDatabase db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(AthleteDao.class);
        registerDaoClass(ChampionshipDao.class);
        registerDaoClass(UserDao.class);
        registerDaoClass(GameUserDao.class);
        registerDaoClass(GameOpponentDao.class);
    }
    
    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }
    
    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }
    
}
