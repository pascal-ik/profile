package pamtech.com.weatherapi.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import pamtech.com.weatherapi.database.dao.WeatheMapDao;
import pamtech.com.weatherapi.database.dao.WeatheMapDao_Impl;

@SuppressWarnings("unchecked")
public class WeatherDb_Impl extends WeatherDb {
  private volatile WeatheMapDao _weatheMapDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(3) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `WeatherMapData` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `zip_code` TEXT, `timestamp` REAL NOT NULL, `response` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"8aaaa8cd26f055d19cfe0a5292954631\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `WeatherMapData`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsWeatherMapData = new HashMap<String, TableInfo.Column>(4);
        _columnsWeatherMapData.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsWeatherMapData.put("zip_code", new TableInfo.Column("zip_code", "TEXT", false, 0));
        _columnsWeatherMapData.put("timestamp", new TableInfo.Column("timestamp", "REAL", true, 0));
        _columnsWeatherMapData.put("response", new TableInfo.Column("response", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWeatherMapData = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWeatherMapData = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWeatherMapData = new TableInfo("WeatherMapData", _columnsWeatherMapData, _foreignKeysWeatherMapData, _indicesWeatherMapData);
        final TableInfo _existingWeatherMapData = TableInfo.read(_db, "WeatherMapData");
        if (! _infoWeatherMapData.equals(_existingWeatherMapData)) {
          throw new IllegalStateException("Migration didn't properly handle WeatherMapData(pamtech.com.weatherapi.database.entities.WeatherMapData).\n"
                  + " Expected:\n" + _infoWeatherMapData + "\n"
                  + " Found:\n" + _existingWeatherMapData);
        }
      }
    }, "8aaaa8cd26f055d19cfe0a5292954631", "14a238585adbec9ce45e6b328cd9a82a");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "WeatherMapData");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `WeatherMapData`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public WeatheMapDao weatheMapDao() {
    if (_weatheMapDao != null) {
      return _weatheMapDao;
    } else {
      synchronized(this) {
        if(_weatheMapDao == null) {
          _weatheMapDao = new WeatheMapDao_Impl(this);
        }
        return _weatheMapDao;
      }
    }
  }
}
