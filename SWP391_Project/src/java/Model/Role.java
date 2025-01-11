/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author thang
 */
public class Role {
    private int roleId ;
    private String roleName;

    public Role(int RoleId, String RoleName) {
        this.roleId = RoleId;
        this.roleName = RoleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int RoleId) {
        this.roleId = RoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String RoleName) {
        this.roleName = RoleName;
    }
    
    
}
