package com.dacsoft.musikcube.undupe;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DupeDetail {
    int songid;//= rs.getInt("songid");
    String title;// = rs.getString("title");
    String artist;// = rs.getString("artist");
    String album;// = rs.getString("album");
    int rowct;// = rs.getInt("rowct");
    String filename;

    DupeDetail(ResultSet rs) throws SQLException {
        songid = rs.getInt("songid");
        title = rs.getString("title");
        artist = rs.getString("artist");
        album = rs.getString("album");
        rowct = rs.getInt("rowct");
        filename = rs.getString("filename");
    }

    @Override
    public String toString() {
        return "" + rowct + " of " + songid + ":" + title + "/" + artist + "/" + album;
    }

}


