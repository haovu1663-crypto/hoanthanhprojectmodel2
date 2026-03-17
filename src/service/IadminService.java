package service;

import model.Admin;

public interface IadminService {
    Admin loginAdmin(String username, String password);
    void addAdmin(Admin admin);

}
