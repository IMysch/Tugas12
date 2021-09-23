package com.example.movieapp.data;
rt androidx.room.Database;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.movieapp.model.Result;

@Database(entities = {Result.class},version = 2, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class Movieapp extends RoomDatabase {
    public abstract MovieDao movieDao();

}