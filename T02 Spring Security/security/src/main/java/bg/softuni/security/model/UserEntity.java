package bg.softuni.security.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String email;

  private String password;
  @Column(nullable = false)
  private String firstName;
  @Column(nullable = false)
  private String lastName;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<UserRoleEntity> userRoles = new ArrayList<>();

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<UserRoleEntity> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(List<UserRoleEntity> userRoles) {
    this.userRoles = userRoles;
  }

  public UserEntity addRole(UserRoleEntity userRole) {
    this.userRoles.add(userRole);
    return this;
  }

  @Override
  public String toString() {
    return "UserEntity{" +
        "email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", userRoles=" + userRoles +
        '}';
  }
}
