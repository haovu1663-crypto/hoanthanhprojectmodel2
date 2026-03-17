package service.impl;

import dao.impl.AdminDao;
import model.Admin;
import service.IadminService;

public class AdminService implements IadminService {
    private static final AdminDao adminDao = new AdminDao();
    @Override
    public Admin loginAdmin(String username, String password) {
        Admin admin = adminDao.findAdminByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        else return null;
    }

    @Override
    public void addAdmin(Admin admin) {
        adminDao.addAdmin(admin);
    }


}
