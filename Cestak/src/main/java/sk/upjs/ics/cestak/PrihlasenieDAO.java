/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.cestak;

/**
 *
 * @author Majlo
 */
public interface PrihlasenieDAO {
    
    Pouzivatel savePouzivatela(Pouzivatel pouzivatel);
    
    void saveLogin(Login login);
    
    Login verifyLogin(Login login);
    
    boolean verifyOnlyLogin(Login login);
}
