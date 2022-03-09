// ICommonAidlInterface.aidl
package com.optima.plugin.repluginlib;

// Declare any non-default types here with import statements
import com.optima.plugin.repluginlib.module.User;
interface ICommonAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

      void say(String word);
      void setUser(in User user,int postion);
      void addUser(in User user);
      List<User> getUsers();
      User getUser(int position);
}
