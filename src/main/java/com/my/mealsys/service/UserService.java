package com.my.mealsys.service;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface UserService {

    Map signUp(String acot,String password);

    Map login(String acot, String password, HttpSession httpSession);
}
