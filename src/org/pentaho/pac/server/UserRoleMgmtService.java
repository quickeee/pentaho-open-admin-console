package org.pentaho.pac.server;

import java.util.List;

import org.pentaho.pac.client.PentahoSecurityException;
import org.pentaho.pac.client.users.DuplicateUserException;
import org.pentaho.pac.client.users.NonExistingUserException;

/*package private */ class UserRoleMgmtService implements IUserRoleMgmtService {
  IUserRoleDAO userRoleDAO = null;

  public UserRoleMgmtService() {
    userRoleDAO = UserRoleDAOFactory.getDAO();
  }
  
  public void createRole(IPentahoRole newRole) throws DAOException, PentahoSecurityException {
    if (hasCreateRolePerm(newRole)) {
      userRoleDAO.createRole(newRole);
    } else {
      throw new PentahoSecurityException();
    }
  }

  public void createUser(IPentahoUser newUser) throws DuplicateUserException, DAOException, PentahoSecurityException {
    if (hasCreateUserPerm(newUser)) {
      userRoleDAO.createUser(newUser);
    } else {
      throw new PentahoSecurityException();
    }
  }

  public void deleteRole(String roleName) throws NonExistingRoleException, DAOException, PentahoSecurityException {
    IPentahoRole role = userRoleDAO.getRole(roleName);
    if (role != null) {
      deleteRole(role);
    } else {
      throw new NonExistingRoleException(roleName);
    }
  }
  
  public void deleteRole(IPentahoRole role) throws NonExistingRoleException, DAOException, PentahoSecurityException {
    if (hasDeleteRolePerm(role)) {
      userRoleDAO.deleteRole(role);
    } else {
      throw new PentahoSecurityException();
    }
  }

  public void deleteUser(String userName) throws NonExistingUserException, DAOException, PentahoSecurityException {
    IPentahoUser role = userRoleDAO.getUser(userName);
    if (role != null) {
      deleteUser(role);
    } else {
      throw new NonExistingUserException( userName );
    }
  }
  
  public void deleteUser(IPentahoUser user) throws NonExistingUserException, DAOException, PentahoSecurityException {
    if (hasDeleteUserPerm(user)) {
      userRoleDAO.deleteUser(user);
    } else {
      throw new PentahoSecurityException();
    }    
  }

  public IPentahoRole getRole(String name) throws DAOException {
    return userRoleDAO.getRole(name);
  }

  public List<IPentahoRole> getRoles() throws DAOException {
    return userRoleDAO.getRoles();
  }

  public IPentahoUser getUser(String name) throws DAOException {
    return userRoleDAO.getUser(name);
  }

  public List<IPentahoUser> getUsers() throws DAOException {
    return userRoleDAO.getUsers();
  }

  public void updateRole(IPentahoRole role) throws DAOException, PentahoSecurityException, NonExistingRoleException {
    if (hasUpdateRolePerm(role)) {
      userRoleDAO.updateRole(role);
    } else {
      throw new PentahoSecurityException();
    }    
  }

  public void updateUser(IPentahoUser user) throws DAOException, PentahoSecurityException, NonExistingUserException {
    if (hasUpdateUserPerm(user)) {
      userRoleDAO.updateUser(user);
    } else {
      throw new PentahoSecurityException();
    }    
  }
  
  public void beginTransaction()  throws DAOException {
    userRoleDAO.beginTransaction();
  }
    
  public void commitTransaction()  throws DAOException {
    userRoleDAO.commitTransaction();
  }
  
  public void rollbackTransaction()  throws DAOException {
    userRoleDAO.rollbackTransaction();
  }
  
  public void closeSession() {
    userRoleDAO.closeSession();
  }
  
  protected boolean hasCreateUserPerm(IPentahoUser user) {
    return true;
  }
  
  protected boolean hasCreateRolePerm(IPentahoRole role) {
    return true;
  }
  
  protected boolean hasUpdateUserPerm(IPentahoUser user) {
    return true;
  }
  
  protected boolean hasUpdateRolePerm(IPentahoRole role) {
    return true;
  }
  
  protected boolean hasDeleteUserPerm(IPentahoUser user) {
    return true;
  }
  
  protected boolean hasDeleteRolePerm(IPentahoRole role) {
    return true;
  }
}
