package com.example.edwin.nav_sql_example.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.edwin.nav_sql_example.modelo.Nota;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="db_notas";
    public static final String TABLA_NOTAS="Nota";
    public static final String CAMPO_ID="evaluacion";
    public static final String CAMPO_NOTA="nota";
    public static final String CREAR_TABLA_USUARIO="CREATE TABLE " + TABLA_NOTAS +"("+CAMPO_ID+" TEXT,"+CAMPO_NOTA+" TEXT)";

    public static DBHelper myDB = null;
    private Context context;
    SQLiteDatabase db;

    public static DBHelper getInstance(Context ctx){
        if(myDB == null){
            myDB = new DBHelper(ctx.getApplicationContext());
        }
        return myDB;
    }

    public DBHelper(Context context){
        super(context, DB_NAME, null, 1);
        this.context=context;
        db=this.getWritableDatabase();
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+CAMPO_NOTA);
        onCreate(db);
    }
    public boolean add(Nota n){
        ContentValues values= new ContentValues();

        values.put(CAMPO_ID,n.getEvaluacion());
        values.put(CAMPO_NOTA,n.getNota());

        db.insert(TABLA_NOTAS,null,values);
        Toast.makeText(context, "Insertado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }
    public Nota findNota(String evaluacion){
        Nota n;

        String[] parametros = {evaluacion};
        String[] campos = {CAMPO_NOTA};

        try{
            Cursor cursor = db.query(TABLA_NOTAS,campos,CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            n = new Nota(evaluacion,Double.parseDouble(cursor.getString(0)));
        }
        catch (Exception e){
            n=null;
        }
        return n;
    }
    public boolean editUser(Nota n){
        String[] parametros = {n.getEvaluacion()};
        String[] campos = {CAMPO_NOTA};
        ContentValues values = new ContentValues();
        values.put(CAMPO_NOTA,n.getNota());
        db.update(TABLA_NOTAS,values,CAMPO_ID+"=?",parametros);
        Toast.makeText(context,"Usuario Actualizado con exito",Toast.LENGTH_SHORT).show();
        return true;
    }
    public boolean deleteUser(String dui){
        String[] parametros ={dui};
        db.delete(TABLA_NOTAS,CAMPO_ID+"=?",parametros);
        Toast.makeText(context, "Usuario Eliminado con exito", Toast.LENGTH_LONG).show();
        return true;
    }
}
