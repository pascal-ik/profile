package pamtech.com.weatherapi.database.dao;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EmptyResultSetException;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import io.reactivex.Single;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.concurrent.Callable;
import pamtech.com.weatherapi.database.entities.WeatherMapData;

@SuppressWarnings("unchecked")
public class WeatheMapDao_Impl implements WeatheMapDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfWeatherMapData;

  public WeatheMapDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWeatherMapData = new EntityInsertionAdapter<WeatherMapData>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `WeatherMapData`(`id`,`zip_code`,`timestamp`,`response`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WeatherMapData value) {
        stmt.bindLong(1, value.getId());
        if (value.getZip_code() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getZip_code());
        }
        stmt.bindDouble(3, value.getTimestamp());
        if (value.getResponse() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getResponse());
        }
      }
    };
  }

  @Override
  public void insertResp(WeatherMapData weatherMapData) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfWeatherMapData.insert(weatherMapData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Single<WeatherMapData> getWeatherFromZip(String zip_code) {
    final String _sql = "SELECT * FROM WeatherMapData WHERE zip_code = ?;";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (zip_code == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, zip_code);
    }
    return Single.fromCallable(new Callable<WeatherMapData>() {
      @Override
      public WeatherMapData call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfZipCode = _cursor.getColumnIndexOrThrow("zip_code");
          final int _cursorIndexOfTimestamp = _cursor.getColumnIndexOrThrow("timestamp");
          final int _cursorIndexOfResponse = _cursor.getColumnIndexOrThrow("response");
          final pamtech.com.weatherapi.database.entities.WeatherMapData _result;
          if(_cursor.moveToFirst()) {
            _result = new pamtech.com.weatherapi.database.entities.WeatherMapData();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _result.setId(_tmpId);
            final java.lang.String _tmpZip_code;
            _tmpZip_code = _cursor.getString(_cursorIndexOfZipCode);
            _result.setZip_code(_tmpZip_code);
            final double _tmpTimestamp;
            _tmpTimestamp = _cursor.getDouble(_cursorIndexOfTimestamp);
            _result.setTimestamp(_tmpTimestamp);
            final java.lang.String _tmpResponse;
            _tmpResponse = _cursor.getString(_cursorIndexOfResponse);
            _result.setResponse(_tmpResponse);
          } else {
            _result = null;
          }
          if(_result == null) {
            throw new EmptyResultSetException("Query returned empty result set: " + _statement.getSql());
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
