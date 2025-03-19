package model;

import java.time.LocalDate;

public class User {

    private int userId;
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private String phone;
    private String email;
    private String gender;
    private int roleId;
    private int isDelete;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    private User(UserBuilder builder) {
        this.userId = builder.userId;
        this.username = builder.username;
        this.password = builder.password;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.phone = builder.phone;
        this.email = builder.email;
        this.gender = builder.gender;
        this.roleId = builder.roleId;
        this.isDelete = builder.isDelete;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public static class UserBuilder {

        private int userId;
        private String username;
        private String password;
        private String lastName;
        private String firstName;
        private String phone;
        private String email;
        private String gender;
        private int roleId;
        private int isDelete = 0;
        private LocalDate createdAt = LocalDate.now();
        private LocalDate updatedAt = LocalDate.now();

        public UserBuilder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public UserBuilder roleId(int roleId) {
            this.roleId = roleId;
            return this;
        }

        public UserBuilder delete(int isDelete) {
            this.isDelete = isDelete;
            return this;
        }

        public UserBuilder createdAt(LocalDate createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserBuilder updatedAt(LocalDate updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public int getRoleId() {
        return roleId;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
