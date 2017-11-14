package com.blogspot.ramannada.quizapp.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import com.blogspot.ramannada.quizapp.model.Question;

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
        sqLiteDatabase.execSQL(QuestionTableEntry.CREATE_QUESTION_TABLE);
        sqLiteDatabase.execSQL(
                "INSERT INTO " + QuestionTableEntry.TABLE_NAME + " (" +
                        QuestionTableEntry.COLUMN_QUESTION + ", " +
                        QuestionTableEntry.COLUMN_ANSWER_A + ", " +
                        QuestionTableEntry.COLUMN_ANSWER_B + ", " +
                        QuestionTableEntry.COLUMN_ANSWER_C + ", " +
                        QuestionTableEntry.COLUMN_ANSWER_D + ", " +
                        QuestionTableEntry.COLUMN_KEY_ANSWER + ") VALUES (" +
                        "'Siapa Rasul yang membawa agama Islam?', " +
                        "'Isa', 'Musa', 'Muhammad', 'Daud', 'a'), (" +
                        "'Rukun iman ada berapa?', " +
                        "'5', '4', '7', '6', 'd'), (" +
                        "'Rukun Islam ada berapa?', " +
                        "'5', '4', '7', '6', 'a'), (" +
                        "'Berapa asmaul husna yang dimiliki Allah SWT?', " +
                        "'98', '97', '99', '100', 'c'), (" +
                        "'Surat apa yang pertama kali diterima Rasul Muhammad SAW?', " +
                        "'Al-Fatihah', 'Al-Alaq', 'Al-Ikhlas', 'Al-Ashr', 'b')"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public static class QuestionTableEntry implements BaseColumns {
        public static final String TABLE_NAME = "table_question";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_ANSWER_A = "answer_a";
        public static final String COLUMN_ANSWER_B = "answer_b";
        public static final String COLUMN_ANSWER_C = "answer_c";
        public static final String COLUMN_ANSWER_D = "answer_d";
        public static final String COLUMN_KEY_ANSWER = "key_answer";
        public static final String CREATE_QUESTION_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        COLUMN_QUESTION + " TEXT NOT NULL, " +
                        COLUMN_ANSWER_A + " TEXT NOT NULL, " +
                        COLUMN_ANSWER_B + " TEXT NOT NULL, " +
                        COLUMN_ANSWER_C + " TEXT NOT NULL, " +
                        COLUMN_ANSWER_D + " TEXT NOT NULL, " +
                        COLUMN_KEY_ANSWER + " TEXT NOT NULL)";
    }

    public Question getQuestion(int i) {
        String query = "SELECT * FROM " + QuestionTableEntry.TABLE_NAME + " WHERE " +
                QuestionTableEntry.COLUMN_ID + " = i";
        SQLiteDatabase dbRead = this.getReadableDatabase();

        Cursor cursor = dbRead.query(QuestionTableEntry.TABLE_NAME, new String[] {
                QuestionTableEntry.COLUMN_ID, QuestionTableEntry.COLUMN_QUESTION,
                QuestionTableEntry.COLUMN_ANSWER_A, QuestionTableEntry.COLUMN_ANSWER_B,
                QuestionTableEntry.COLUMN_ANSWER_C, QuestionTableEntry.COLUMN_ANSWER_D,
                QuestionTableEntry.COLUMN_KEY_ANSWER
        }, QuestionTableEntry.COLUMN_ID + "= ?", new String[]{String.valueOf(i)},
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            Question question = new Question(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3),cursor.getString(4),
                    cursor.getString(5), cursor.getString(6));
            return question;
        } else {
            return null;
        }

    }

    public int getQuestionSize() {
        String query = "SELECT * FROM " + QuestionTableEntry.TABLE_NAME;

        SQLiteDatabase dbRead = this.getReadableDatabase();
        Cursor cursor = dbRead.rawQuery(query, null);
        cursor.moveToFirst();

        return cursor.getCount();

    }
}
