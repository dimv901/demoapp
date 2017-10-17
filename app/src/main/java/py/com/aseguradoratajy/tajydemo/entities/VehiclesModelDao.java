package py.com.aseguradoratajy.tajydemo.entities;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "VEHICLES_MODEL".
*/
public class VehiclesModelDao extends AbstractDao<VehiclesModel, Long> {

    public static final String TABLENAME = "VEHICLES_MODEL";

    /**
     * Properties of entity VehiclesModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ModelCode = new Property(1, Integer.class, "modelCode", false, "MODEL_CODE");
        public final static Property Description = new Property(2, String.class, "description", false, "DESCRIPTION");
        public final static Property BrandId = new Property(3, Integer.class, "brandId", false, "BRAND_ID");
    }


    public VehiclesModelDao(DaoConfig config) {
        super(config);
    }
    
    public VehiclesModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"VEHICLES_MODEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"MODEL_CODE\" INTEGER," + // 1: modelCode
                "\"DESCRIPTION\" TEXT," + // 2: description
                "\"BRAND_ID\" INTEGER);"); // 3: brandId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"VEHICLES_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, VehiclesModel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer modelCode = entity.getModelCode();
        if (modelCode != null) {
            stmt.bindLong(2, modelCode);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(3, description);
        }
 
        Integer brandId = entity.getBrandId();
        if (brandId != null) {
            stmt.bindLong(4, brandId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, VehiclesModel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer modelCode = entity.getModelCode();
        if (modelCode != null) {
            stmt.bindLong(2, modelCode);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(3, description);
        }
 
        Integer brandId = entity.getBrandId();
        if (brandId != null) {
            stmt.bindLong(4, brandId);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public VehiclesModel readEntity(Cursor cursor, int offset) {
        VehiclesModel entity = new VehiclesModel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // modelCode
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // description
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3) // brandId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, VehiclesModel entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setModelCode(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setDescription(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setBrandId(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(VehiclesModel entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(VehiclesModel entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(VehiclesModel entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}