package web.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Component
@Table(name = "users")
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column
   private String email;

   @Column
   private Integer age;

   @Column
   private String password;

   @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
   @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id"))
   @JsonManagedReference
   private Set<Role> roles = new HashSet<>();

   public User() {
   }

   public void setRoles(String roles) {
      if (roles.contains("ADMIN")) {
         this.roles.add(new Role("ROLE_ADMIN"));
      }
      if (roles.contains("USER")) {
         this.roles.add(new Role("ROLE_USER"));
      }
   }

   public User(String firstName, String lastName, String email, Integer age, String password, Set<Role> roles) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.age = age;
      this.password = password;
      this.roles = roles;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
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

   public Integer getAge() {
      return age;
   }

   public void setAge(Integer age) {
      this.age = age;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @Override
   @JsonIgnore
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return roles;
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return email;
   }

   @Override
   @JsonIgnore
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   @JsonIgnore
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   @JsonIgnore
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   @JsonIgnore
   public boolean isEnabled() {
      return true;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", email='" + email + '\'' +
              ", age='" + age + '\'' +
              ", password='" + password + '\'' +
              ", roles=" + roles +
              '}';
   }
}