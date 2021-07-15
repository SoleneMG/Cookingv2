package com.example.cookingv2.data.database.impl;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Room;

import com.example.cookingv2.data.database.UserDao;
import com.example.cookingv2.data.database.impl.model.RoomUser;
import com.example.cookingv2.model.User;

import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class RoomUserDao implements UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract void roomInsert(RoomUser user);

    @Query("SELECT * FROM user")
    abstract List<RoomUser> roomGetAll();

    @Query("DELETE FROM user")
    abstract void roomdeleteAll();

    @Query("SELECT * FROM user WHERE id LIKE :userId")
    abstract RoomUser roomfindUserById(String userId);

    @Override
    public void insert(User user) {
        RoomUser roomUser = new RoomUser(user.publicId, user.id, user.email);
        roomInsert(roomUser);
    }

    @Override
    public List<User> getAll() {
        List<RoomUser> roomUserList = roomGetAll();
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < roomUserList.size(); i++) {
            User user = new User(roomUserList.get(i).publicId, roomUserList.get(i).id, roomUserList.get(i).email);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public void deleteAll() {
        roomdeleteAll();
    }

    @Override
    public User findUserById(String id) {
        RoomUser roomUser = roomfindUserById(id);
        return new User(roomUser.publicId, roomUser.id, roomUser.email);
    }
}
