package knowledge_seek.com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import knowledge_seek.com.Alarm;

/**
 * Created by sjw on 2015-10-02.
 */
public class Database extends SQLiteOpenHelper {

    static Database instance = null;
    static SQLiteDatabase database = null;

    //데이터베이스 저장경로 변경
    static final String DATABASE_NAME = "alarm_db";
    static final int DATABASE_VERSION = 1;

    public static final String ALARM_TABLE = "alarm_tb";
    public static final String COLUMN_ALARM_ID = "_id";
    public static final String COLUMN_ALARM_ACTIVE = "alarm_active";
    public static final String COLUMN_ALARM_TIME = "alarm_time";
    public static final String COLUMN_ALARM_DAYS = "alarm_days";
    public static final String COLUMN_ALARM_TYPE = "alarm_type";
    public static final String COLUMN_ALARM_STAR = "alarm_star";
    /*public static final String COLUMN_ALARM_TONE = "alarm_tone";*/
    //public static final String COLUMN_ALARM_REPEAT = "alarm_repeat";
    /*public static final String COLUMN_ALARM_VIBRATE = "alarm_vibrate";*/
    /*public static final String COLUMN_ALARM_NAME = "alarm_name";*/
    public static final String COLUMN_ALARM_VOLUME = "alarm_volume";

    //데이터베이스 객체 생성
    public static void init(Context context){
        if(instance == null){
            instance = new Database(context);
        }
    }

    //데이터베이스 쓰기로 읽어오기
    public static SQLiteDatabase getDatabase(){
        if(database == null){
            database = instance.getWritableDatabase();
        }
        return database;
    }

    public static void deactivate(){
        if(database != null && database.isOpen()){
            database.close();
        }
        database = null;
        instance = null;
    }

    public static long create(Alarm alarm){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ALARM_ACTIVE, alarm.getAlarmActive());
        cv.put(COLUMN_ALARM_TIME, alarm.getAlarmTimeString());

        try{
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = null;
            oos = new ObjectOutputStream(bos);
            oos.writeObject(alarm.getDays());
            byte[] buff = bos.toByteArray();

            cv.put(COLUMN_ALARM_DAYS, buff);

        } catch(Exception e){

        }

        /*cv.put(COLUMN_ALARM_TONE, alarm.getAlarmTonePath());*/
        cv.put(COLUMN_ALARM_TYPE, alarm.getType().ordinal());
        cv.put(COLUMN_ALARM_STAR, alarm.getStar().ordinal());
        //cv.put(COLUMN_ALARM_REPEAT, alarm.getRepeat().ordinal());
        /*cv.put(COLUMN_ALARM_VIBRATE, alarm.getVibrate());*/
        /*cv.put(COLUMN_ALARM_NAME, alarm.getAlarmName());*/
        cv.put(COLUMN_ALARM_VOLUME, alarm.getVolume());

        return getDatabase().insert(ALARM_TABLE, null, cv);
    }

    public static int update(Alarm alarm){
        Log.d("-진우- 업데이트 : ", alarm.toString());
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ALARM_ACTIVE, alarm.getAlarmActive());
        cv.put(COLUMN_ALARM_TIME, alarm.getAlarmTimeString());

        try{
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = null;
            oos = new ObjectOutputStream(bos);
            oos.writeObject(alarm.getDays());
            byte[] buff = bos.toByteArray();

            cv.put(COLUMN_ALARM_DAYS, buff);

        }catch (Exception e){

        }

        /*cv.put(COLUMN_ALARM_TONE, alarm.getAlarmTonePath());*/
        cv.put(COLUMN_ALARM_TYPE, alarm.getType().ordinal());
        cv.put(COLUMN_ALARM_STAR, alarm.getStar().ordinal());
        //cv.put(COLUMN_ALARM_REPEAT, alarm.getRepeat().ordinal());
        /*cv.put(COLUMN_ALARM_VIBRATE, alarm.getVibrate());*/
        /*cv.put(COLUMN_ALARM_NAME, alarm.getAlarmName());*/
        cv.put(COLUMN_ALARM_VOLUME, alarm.getVolume());

        return getDatabase().update(ALARM_TABLE, cv, "_id=" + alarm.getId(), null);
    }

    public static int deleteEntry(Alarm alarm){
        Log.d("-진우- 알람삭제 ", alarm.toString());
        return deleteEntry(alarm.getId());
    }

    public static int deleteEntry(int id){
        return getDatabase().delete(ALARM_TABLE, COLUMN_ALARM_ID + "=" + id, null);
    }

    public static int deleteAll(){
        return getDatabase().delete(ALARM_TABLE, "1", null);
    }

    public static Alarm getAlarm(int id){
        String[] columns = new String[]{
                COLUMN_ALARM_ID,
                COLUMN_ALARM_ACTIVE,
                COLUMN_ALARM_TIME,
                COLUMN_ALARM_DAYS,
                COLUMN_ALARM_TYPE,
                COLUMN_ALARM_STAR,
                COLUMN_ALARM_VOLUME
                //COLUMN_ALARM_REPEAT
        };

        Cursor c = getDatabase().query(ALARM_TABLE, columns, COLUMN_ALARM_ID+"="+id, null, null, null, null);

        Alarm alarm = null;

        if(c.moveToFirst()){
            alarm = new Alarm();
            alarm.setId(c.getInt(1));
            alarm.setAlarmActive(c.getInt(2) == 1);
            alarm.setAlarmTime(c.getString(3));
            byte[] repeatDaysBytes = c.getBlob(4);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(repeatDaysBytes);
            try{
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                Alarm.Day[] repeatDays;
                Object object = objectInputStream.readObject();
                if(object instanceof Alarm.Day[]){
                    repeatDays = (Alarm.Day[])object;
                    alarm.setDays(repeatDays);
                }
            }catch (StreamCorruptedException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }

            /*alarm.setDifficulty(Alarm.Difficulty.values()[c.getInt(5)]);*/
            alarm.setType(Alarm.Type.values()[c.getInt(5)]);
            alarm.setStar(Alarm.Star.values()[c.getInt(6)]);
            /*alarm.setAlarmTonePath(c.getString(7));*/
            //alarm.setRepeat(Alarm.Repeat.values()[c.getInt(7)]);
            /*alarm.setVibrate(c.getInt(7)==1);*/
            /*alarm.setAlarmName(c.getString(8));*/
            alarm.setVolume(c.getInt(7));
        }
        c.close();
        return alarm;
    }

    public static Cursor getCursor(){
        String[] columns = new String[]{
                COLUMN_ALARM_ID,
                COLUMN_ALARM_ACTIVE,
                COLUMN_ALARM_TIME,
                COLUMN_ALARM_DAYS,
                COLUMN_ALARM_TYPE,
                COLUMN_ALARM_STAR,
                COLUMN_ALARM_VOLUME
                //COLUMN_ALARM_REPEAT
        };
        return getDatabase().query(ALARM_TABLE, columns, null, null, null, null, null);
    }


    Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL("CREATE TABLE IF NOT EXISTS " + ALARM_TABLE + " ("
                + COLUMN_ALARM_ID + " INTEGER primary key autoincrement, "
                + COLUMN_ALARM_ACTIVE + " INTEGER NOT NULL, "
                + COLUMN_ALARM_TIME + " TEXT NOT NULL, "
                + COLUMN_ALARM_DAYS + " BLOB NOT NULL, "
                + COLUMN_ALARM_TYPE + " INTEGER NOT NULL, "
                + COLUMN_ALARM_STAR + " INTEGER NOT NULL, "
                + COLUMN_ALARM_TONE + " TEXT NOT NULL)");
                *//*+ COLUMN_ALARM_VIBRATE + " INTEGER NOT NULL, "*//*
                *//*+ COLUMN_ALARM_NAME + " TEXT NOT NULL" ); */
        db.execSQL("CREATE TABLE IF NOT EXISTS " + ALARM_TABLE + " ("
                + COLUMN_ALARM_ID + " INTEGER primary key autoincrement, "
                + COLUMN_ALARM_ACTIVE + " INTEGER NOT NULL, "
                + COLUMN_ALARM_TIME + " TEXT NOT NULL, "
                + COLUMN_ALARM_DAYS + " BLOB NOT NULL, "
                + COLUMN_ALARM_TYPE + " INTEGER NOT NULL, "
                + COLUMN_ALARM_STAR + " INTEGER NOT NULL, "
                + COLUMN_ALARM_VOLUME + " INTEGER NOT NULL)");
                /*+ COLUMN_ALARM_VIBRATE + " INTEGER NOT NULL, "*/
                /*+ COLUMN_ALARM_NAME + " TEXT NOT NULL" ); */
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ALARM_TABLE);
        onCreate(db);
    }

    public static List<Alarm> getAll(){
        List<Alarm> alarms = new ArrayList<Alarm>();
        Cursor cursor = Database.getCursor();
        if(cursor.moveToFirst()){

            do{
                // COLUMN_ALARM_ID,
                // COLUMN_ALARM_ACTIVE,
                // COLUMN_ALARM_TIME,
                // COLUMN_ALARM_DAYS,
                // COLUMN_ALARM_TYPE,
                // COLUMN_ALARM_STAR,
                // COLUMN_ALARM_REPEAT
                //COLUMN_ALARM_VOLUME

                Alarm alarm = new Alarm();
                alarm.setId(cursor.getInt(0));
                alarm.setAlarmActive(cursor.getInt(1) == 1);
                alarm.setAlarmTime(cursor.getString(2));
                byte[] repeatDaysBytes = cursor.getBlob(3);

                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(repeatDaysBytes);
                try{
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    Alarm.Day[] repeatDays;
                    Object object = objectInputStream.readObject();
                    if(object instanceof Alarm.Day[]){
                        repeatDays = (Alarm.Day[])object;
                        alarm.setDays(repeatDays);
                    }
                }catch (StreamCorruptedException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }

                /*alarm.setDifficulty(Alarm.Difficulty.values()[cursor.getInt(4)]);*/
                alarm.setType(Alarm.Type.values()[cursor.getInt(4)]);
                alarm.setStar(Alarm.Star.values()[cursor.getInt(5)]);
                /*alarm.setAlarmTonePath(cursor.getString(6));*/
                //alarm.setRepeat(Alarm.Repeat.values()[cursor.getInt(6)]);
                /*alarm.setVibrate(cursor.getInt(6) == 1);*/
                /*alarm.setAlarmName(cursor.getString(7));*/
                alarm.setVolume(cursor.getInt(6));

                alarms.add(alarm);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return alarms;
    }
}
