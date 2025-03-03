package paging.wrapper.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = User.TABLE_NAME)
public class User {
  public static final String ID = "_id";
  public static final String TABLE_NAME = "user";

  @PrimaryKey
  @ColumnInfo(name = ID, index = true)
  public final int id;

  public final String login;
  public final String avatarUrl;

  public User(int id, String login, String avatarUrl) {
    this.id = id;
    this.login = login;
    this.avatarUrl = avatarUrl;
  }
}
