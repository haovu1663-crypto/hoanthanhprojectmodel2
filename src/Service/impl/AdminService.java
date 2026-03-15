package Service.impl;

import Dao.impl.AdminDao;
import Model.Admin;
import Service.IadminService;

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
