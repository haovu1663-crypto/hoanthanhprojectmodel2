package Dao;

import Model.Admin;

public interface IAdminDao {
   Admin findAdminByUsername(String name);
}
