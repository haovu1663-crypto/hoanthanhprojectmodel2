package Service;

import Model.Admin;

public interface IadminService {
    Admin loginAdmin(String username, String password);
}
