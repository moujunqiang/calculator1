package cn.edu.cuz.zhengjun.calculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;


public class HistoryDao {
    Context context;
    HistoryDBHelper dbHelper;

    public HistoryDao(Context context) {
        this.context = context;
        dbHelper = new HistoryDBHelper(context, "history.db", null, 1);
    }

    public void insertHistory(HistoryBean bean) {

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("history_content", bean.getContent());
        cv.put("history_result", bean.getResult());
        sqLiteDatabase.insert("history_data", null, cv);
    }

    public int DeleteNote(int id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int ret = 0;
        ret = sqLiteDatabase.delete("history_data", "history_id=?", new String[]{id + ""});
        return ret;
    }

    public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String sql = "select * from history_data";
        return sqLiteDatabase.rawQuery(sql, null);
    }


    public List<HistoryBean> queryll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        List<HistoryBean> noteList = new ArrayList<>();
        HistoryBean note;
        String sql = "select * from history_data ";
        Cursor cursor = null;

        cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            note = new HistoryBean();
            note.setResult(cursor.getString(cursor.getColumnIndex("history_result")));
            note.setContent(cursor.getString(cursor.getColumnIndex("history_content")));
            noteList.add(note);
        }

        if (cursor != null) {
            cursor.close();
        }
        if (db != null) {
            db.close();
        }

        return noteList;
    }


}
