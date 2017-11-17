package com.blogspot.ramannada.quizapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.usb.UsbRequest;
import android.provider.BaseColumns;
import android.util.Log;

import com.blogspot.ramannada.quizapp.model.Question;
import com.blogspot.ramannada.quizapp.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramannada on 11/13/2017.
 */

public class DatabaseQuiz extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "com.blogspot.ramannada.quizapp.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseQuiz(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String[] feedQuestionTable = new String[5];
        feedQuestionTable[0] =  "INSERT INTO " + QuestionTable.TABLE_NAME + " " +
                QuestionTable.QUESTION_TABLE_COLUMN + " VALUES " +
                "('Siapa Rasul yang membawa agama Islam?'," +
                "'Isa AS', 'Musa AS', 'Muhammad SAW', 'Daud AS', 'c');";
        feedQuestionTable[1] =  "INSERT INTO " + QuestionTable.TABLE_NAME + " " +
                QuestionTable.QUESTION_TABLE_COLUMN + " VALUES " +
                "('Rukun iman ada berapa?'," +
                "'5', '4', '7', '6', 'd');";
        feedQuestionTable[2] = "INSERT INTO " + QuestionTable.TABLE_NAME + " " +
                QuestionTable.QUESTION_TABLE_COLUMN + " VALUES " +
                "('Rukun Islam ada berapa?'," +
                "'5', '4', '7', '6', 'a');";
        feedQuestionTable[3] = "INSERT INTO " + QuestionTable.TABLE_NAME + " " +
                QuestionTable.QUESTION_TABLE_COLUMN + " VALUES " +
                "('Berapa asmaul husna yang dimiliki Allah SWT?'," +
                "'98', '97', '99', '100', 'c');";
        feedQuestionTable[4] = "INSERT INTO " + QuestionTable.TABLE_NAME + " " +
                QuestionTable.QUESTION_TABLE_COLUMN + " VALUES " +
                "('Ayat dari surat apa yang pertama kali diterima Rasul Muhammad SAW?'," +
                "'Al-Fatihah', 'Al-Alaq', 'Al-Ikhlas', 'Al-Ashr', 'b');";

        sqLiteDatabase.execSQL(UserTable.CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(QuestionTable.CREATE_QUESTION_TABLE);

//        feed question table
        for (int i = 0; i < feedQuestionTable.length; i++) {
            sqLiteDatabase.execSQL(feedQuestionTable[i]);
        }

//        sqLiteDatabase.execSQL(
//                "INSERT INTO " + QuestionTable.TABLE_NAME + " (" +
//                        QuestionTable.COLUMN_QUESTION + ", " +
//                        QuestionTable.COLUMN_ANSWER_A + ", " +
//                        QuestionTable.COLUMN_ANSWER_B + ", " +
//                        QuestionTable.COLUMN_ANSWER_C + ", " +
//                        QuestionTable.COLUMN_ANSWER_D + ", " +
//                        QuestionTable.COLUMN_KEY_ANSWER + ") VALUES (" +
//                        "'Siapa Rasul yang membawa agama Islam?', " +
//                        "'Isa', 'Musa', 'Muhammad', 'Daud', 'c'), (" +
//                        "'Rukun iman ada berapa?', " +
//                        "'5', '4', '7', '6', 'd'), (" +
//                        "'Rukun Islam ada berapa?', " +
//                        "'5', '4', '7', '6', 'a'), (" +
//                        "'Berapa asmaul husna yang dimiliki Allah SWT?', " +
//                        "'98', '97', '99', '100', 'c'), (" +
//                        "'Surat apa yang pertama kali diterima Rasul Muhammad SAW?', " +
//                        "'Al-Fatihah', 'Al-Alaq', 'Al-Ikhlas', 'Al-Ashr', 'b')"
//
//        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(QuestionTable.DROP_QUESTION_TABLE);
        sqLiteDatabase.execSQL(UserTable.DROP_USER_TABLE);
        onCreate(sqLiteDatabase);
    }

    public static class QuestionTable implements BaseColumns {
        static final String TABLE_NAME = "table_question";
        static final String COLUMN_ID = "_id";
        static final String COLUMN_QUESTION = "question";
        static final String COLUMN_ANSWER_A = "answer_a";
        static final String COLUMN_ANSWER_B = "answer_b";
        static final String COLUMN_ANSWER_C = "answer_c";
        static final String COLUMN_ANSWER_D = "answer_d";
        static final String COLUMN_KEY_ANSWER = "key_answer";
        static final String CREATE_QUESTION_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        COLUMN_QUESTION + " TEXT NOT NULL, " +
                        COLUMN_ANSWER_A + " TEXT NOT NULL, " +
                        COLUMN_ANSWER_B + " TEXT NOT NULL, " +
                        COLUMN_ANSWER_C + " TEXT NOT NULL, " +
                        COLUMN_ANSWER_D + " TEXT NOT NULL, " +
                        COLUMN_KEY_ANSWER + " TEXT NOT NULL)";
        static final String DROP_QUESTION_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
        static final String QUESTION_TABLE_COLUMN =
                "(" + COLUMN_QUESTION + ", " +
                        COLUMN_ANSWER_A + ", " +
                        COLUMN_ANSWER_B + ", " +
                        COLUMN_ANSWER_C + ", " +
                        COLUMN_ANSWER_D + ", " +
                        COLUMN_KEY_ANSWER + ")";
    }

    public static class UserTable implements BaseColumns {
        static final String TABLE_NAME = "table_user";
        static final String COLUMN_ID = "_id";
        static final String COLUMN_NAME = "name";
        static final String COLUMN_SCORE = "score";
        static final String CREATE_USER_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_SCORE + " INTEGER NOT NULL)";
        static final String DROP_USER_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
        static final String USER_TABLE_COLUMN =
                "(" + COLUMN_NAME + ", " + COLUMN_SCORE +")";
    }

    public Question getQuestion(int i) {
//        String query = "SELECT * FROM " + QuestionTable.TABLE_NAME + " WHERE " +
//                QuestionTable.COLUMN_ID + " = i";
        SQLiteDatabase dbRead = getReadableDatabase();

        Cursor cursor = dbRead.query(QuestionTable.TABLE_NAME, new String[] {
                QuestionTable.COLUMN_ID, QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_ANSWER_A, QuestionTable.COLUMN_ANSWER_B,
                QuestionTable.COLUMN_ANSWER_C, QuestionTable.COLUMN_ANSWER_D,
                QuestionTable.COLUMN_KEY_ANSWER
        }, QuestionTable.COLUMN_ID + "= ?", new String[]{String.valueOf(i)},
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            Question question = new Question(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3),cursor.getString(4),
                    cursor.getString(5), cursor.getString(6));

            cursor.close();
            dbRead.close();

            return question;
        } else {
            cursor.close();
            dbRead.close();

            return null;
        }

    }

    public int getQuestionSize() {
        String query = "SELECT * FROM " + QuestionTable.TABLE_NAME;

        SQLiteDatabase dbRead = getReadableDatabase();
        Cursor cursor = dbRead.rawQuery(query, null);
        cursor.moveToFirst();
        int size = cursor.getCount();
        cursor.close();
        dbRead.close();

        return size;

    }

    public List<User> getAllUser() {
        SQLiteDatabase dbRead = getReadableDatabase();

        Cursor cursor = dbRead.query(UserTable.TABLE_NAME, new String[] {
                UserTable.COLUMN_ID, UserTable.COLUMN_NAME, UserTable.COLUMN_SCORE
        }, null, null, null, null,
                UserTable.COLUMN_SCORE + " DESC");

        if (cursor.moveToFirst()) {
            List<User> users = new ArrayList<>();

            do {
                users.add(new User(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
                cursor.moveToNext();
            } while (cursor.moveToNext());

            cursor.close();
            dbRead.close();

            return users;
        } else {
            return null;
        }
    }

    public void saveUser(User user) {
        SQLiteDatabase dbWrite = getWritableDatabase();
//        String sql = "INSERT INTO " + UserTable.TABLE_NAME +
//                UserTable.USER_TABLE_COLUMN + " VALUES" + "('" +
//                user.getName() + "', '" + user.getScore() + "')";
//
//        try {
//            dbWrite.rawQuery(sql, null);
//        } catch (SQLException e) {
//            Log.d("saveUser() exception", e.getMessage());
//        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserTable.COLUMN_NAME, user.getName());
        contentValues.put(UserTable.COLUMN_SCORE, user.getScore());

        dbWrite.insert(UserTable.TABLE_NAME, UserTable.COLUMN_ID, contentValues);

        dbWrite.close();
    }

    public int getUserSize() {
        SQLiteDatabase dbRead = getReadableDatabase();
        String sql = "SELECT * FROM " + UserTable.TABLE_NAME;

        Cursor cursor = dbRead.rawQuery(sql, null);
        int i = cursor.getCount();
        cursor.close();
        dbRead.close();

        return i;
    }




}
