package com.example.zzt.whyfi.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.zzt.whyfi.common.App;
import com.example.zzt.whyfi.model.Device;
import com.example.zzt.whyfi.model.Message;

import java.util.LinkedList;

/**
 * Created by zzt on 6/26/16.
 * <p/>
 * Usage:
 */
public class MsgDbHelper extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "messages";

    // Table Names
    public static final String TABLE_MSG = "msg";
    private static final String TABLE_DEV = "device";


    // msg Table - column names
    private static final String KEY_CONTENT = "content";
    private static final String KEY_TIME = "time";
    public static final String KEY_SELF = "self";
    private static final String KEY_DID = "did";

    // DEVICE Table - column names
    private static final String KEY_NAME = "name";
    private static final String KEY_DES = "des";
    private static final String KEY_AVATAR = "avatar";


    // msg Table Create Statements
    private static final String CREATE_TABLE_TODO = "CREATE TABLE "
            + TABLE_MSG + "("
            + KEY_CONTENT + " TEXT PRIMARY KEY ,"
            + KEY_TIME + " TEXT,"
            + KEY_SELF + " INTEGER,"
            + KEY_DID + " TEXT,"
            + " FOREIGN KEY (" + KEY_DID + ") REFERENCES " + TABLE_DEV + "(" + KEY_DID + "));";

    // device table create statement
    private static final String CREATE_TABLE_TAG = "CREATE TABLE " + TABLE_DEV
            + "("
            + KEY_DID + " TEXT PRIMARY KEY ,"
            + KEY_NAME + " TEXT,"
            + KEY_DES + " TEXT,"
            + KEY_AVATAR + " TEXT" + ")";
    public static final String CANONICAL_NAME = MsgDbHelper.class.getCanonicalName();

    public MsgDbHelper() {
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }


    public MsgDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TODO);
        db.execSQL(CREATE_TABLE_TAG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MSG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEV);

        // create new tables
        onCreate(db);
    }

    public long insertMsg(Message message, boolean self) {
        SQLiteDatabase db = getWritableDatabase();

        Device device = message.getDevice();
        if (!checkIsDataAlreadyInDBorNot(TABLE_DEV, KEY_DID, device.getDid())) {
            insertDevice(device);
        } else {
            updateDevice(device);
        }

        ContentValues values = new ContentValues();
        values.put(KEY_CONTENT, message.getMessage());
        values.put(KEY_TIME, message.getTime());
        values.put(KEY_SELF, bool2Int(self));
        values.put(KEY_DID, device.getDid());

        return db.insert(TABLE_MSG, null, values);
    }

    private void updateDevice(Device device) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = getDevContentValues(device);

        db.update(TABLE_DEV, values, KEY_DID + " =? ", new String[]{device.getDid()});
    }

    @NonNull
    private ContentValues getDevContentValues(Device device) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, device.getName());
        values.put(KEY_DES, device.getDes());
        values.putNull(KEY_AVATAR);
        return values;
    }

    private long insertDevice(Device device) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = getDevContentValues(device);
        values.put(KEY_DID, device.getDid());

        return db.insert(TABLE_DEV, null, values);
    }

    public LinkedList<Message> getReceived() {
        return getMessages(false);
    }

    public LinkedList<Message> getSent() {
        return getMessages(true);
    }

    private LinkedList<Message> getMessages(boolean self) {
        LinkedList<Message> res = new LinkedList<>();

        SQLiteDatabase sqldb = getReadableDatabase();
        String Query = "Select * from " + TABLE_MSG + " where " + KEY_SELF + " = " + bool2Int(self);
        Cursor cursor = sqldb.rawQuery(Query, null);

        if (cursor.moveToFirst()) {
            do {
                Device device = getDevice(cursor.getString(cursor.getColumnIndex(KEY_DID)));
                if (device == null) {
                    Log.e(CANONICAL_NAME, "no such device");
                }
                Message message = new Message(device,
                        cursor.getString(cursor.getColumnIndex(KEY_CONTENT)),
                        cursor.getString(cursor.getColumnIndex(KEY_TIME)));
                res.add(message);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return res;
    }

    private Device getDevice(String did) {
        SQLiteDatabase sqldb = getReadableDatabase();
        String Query = "Select * from " + TABLE_DEV + " where " + KEY_DID + " = '" + did + "'";
        Cursor cursor = sqldb.rawQuery(Query, null);
        if (cursor.moveToFirst()) {
            return new Device(
                    cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                    Device.SYM_DEF_APP_ICON,
                    cursor.getString(cursor.getColumnIndex(KEY_DES))
            );
        }
        return null;
    }

    private int bool2Int(boolean self) {
        return self ? 1 : 0;
    }

    public boolean checkIsDataAlreadyInDBorNot(String TableName,
                                               String dbfield, String fieldValue) {
        SQLiteDatabase sqldb = getReadableDatabase();
        String Query = "Select * from " + TableName + " where " + dbfield + " = '" + fieldValue + "'";
        Cursor cursor = sqldb.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
