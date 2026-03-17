package dao;

import model.Admin;

public interface IAdminDao {
   Admin findAdminByUsername(String name);
   void addAdmin(Admin admin);
}
